package ru.simplesys.macrojs

import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context

import scala.scalajs.js
import ru.simplesys.cmntypes._

trait ToLiteralMacro[P <: SCProps] {
  def toLiteralMacro(t: P): js.Dictionary[js.Any]
}

object ToLiteralMacro {
  implicit def materializeToLiteralMacro[P <: SCProps]: ToLiteralMacro[P] = macro materializeToLiteralMacroImpl[P]

  def typeToConvertedValue(c: Context)(typeDef: c.universe.Type, valueAccess: c.universe.Tree): c.universe.Tree = {
    import c.universe._
    def typeToConvertedValueInt(typeDef: c.universe.Type, valueAccess: c.universe.Tree): Option[c.universe.Tree] = {

      //weirdddd
      typeDef.baseType(typeOf[Option[_]].typeSymbol) match {
        case TypeRef(_, _, targs) =>
          val optEx = if (targs.size == 1) {
            val checkedType = typeToConvertedValueInt(targs.head, q"x")
            checkedType match {
              case Some(ex) => q"$valueAccess.map(x => $ex: js.Any)"
              case None => valueAccess
            }
          } else valueAccess
          Some(q"$optEx.orUndefined")
        case NoType =>
          typeDef.baseType(typeOf[scala.collection.Seq[_]].typeSymbol) match {
            case TypeRef(_, _, targs) =>
              val arrEx = if (targs.size == 1) {
                val checkedType = typeToConvertedValueInt(targs.head, q"x")
                checkedType match {
                  case Some(ex) => q"$valueAccess.map(x => $ex: js.Any)"
                  case None => valueAccess
                }
              } else valueAccess

              Some(q"$arrEx.toJSArray")
            case NoType =>
              typeDef.baseType(typeOf[SCProps].typeSymbol) match {
                case TypeRef(_, _, _) =>
                  Some(q"$valueAccess.toJSLiteral")
                case NoType =>
                  typeDef.baseType(typeOf[Either[_, _]].typeSymbol) match {
                    case TypeRef(_, _, targs) =>
                      val access = q"ei"
                      val checkedTypes = targs.map(t => typeToConvertedValueInt(t, access))
                      val leftType = checkedTypes.head.getOrElse(valueAccess)
                      val rightType = checkedTypes.last.getOrElse(valueAccess)

                      Some( q"""$valueAccess match {
                            case Left(ei) => $leftType: js.Any
                            case Right(ei) => $rightType: js.Any
                          }""")
                    case NoType => None
                  }
              }
          }
      }
    }
    typeToConvertedValueInt(typeDef, valueAccess).getOrElse(q"$valueAccess")
  }

  def materializeToLiteralMacroImpl[P <: SCProps : c.WeakTypeTag](c: Context): c.Expr[ToLiteralMacro[P]] = {
    import c.universe._

    val tpeSCProps = weakTypeOf[P]
    val companion = tpeSCProps.typeSymbol.companion



    //here should be getters for vals and vars, incl inherited members
    val tsSCProps = typeOf[SCProps].typeSymbol
    val tsUnit = typeOf[Unit].typeSymbol
    val fields = tpeSCProps.members.collect {case field if field.isPublic &&
      field.isMethod &&
      !field.asMethod.isSetter &&
      !field.asMethod.isConstructor &&
      field.asMethod.returnType.typeSymbol != tsUnit &&
      field.owner.isClass &&
      field.owner.asClass.baseClasses.contains(tsSCProps) &&
      field.owner.asClass != tsSCProps
       => field
    }
    //println(fields)

    //filtering PropOpt fields as they aren't suitable for smartclient
    //also partition by SCPropOpt and other fields
    val (fSCProps, fSimple) = fields.filter {field =>
      field.typeSignature.baseType(typeOf[PropOpt[_]].typeSymbol) match {
        case TypeRef(_, _, _) => false
        case NoType => true

      }
    }.map {field =>
      field.typeSignature.baseType(typeOf[SCPropOpt[_]].typeSymbol) match {
        case TypeRef(_, _, tArg) => (field, tArg.head, true)
        case NoType => (field, field.typeSignature, false)
      }
    }.partition {case (f, t, p) => p}

    val scPropFields = fSCProps.map { case (field, typeDef, _) =>
      val name = field.name.toTermName
      val decoded = name.decodedName.toString
      val returnType = tpeSCProps.decl(name).typeSignature
      q"""t.$name.foreach {v => res.update($decoded, ${typeToConvertedValue(c)(typeDef, q"v")})}"""
    }


    val simpleFields = fSimple.map { case (field, typeDef, _) =>
      val name = field.name.toTermName
      val decoded = name.decodedName.toString
      val returnType = tpeSCProps.decl(name).typeSignature
      q"""res.update($decoded, ${typeToConvertedValue(c)(typeDef, q"t.$name")})"""
    }

    val simpleFieldsExpansion = if (simpleFields.nonEmpty)
      q"..$simpleFields" else q""

    val scPropFieldsExpansion = if (scPropFields.nonEmpty)
      q"""..$scPropFields""" else q""

    //$tpeLower, $tpeUpper,
    val res = c.Expr[ToLiteralMacro[P]] { q"""
    new ToLiteralMacro[$tpeSCProps] {
      import scala.scalajs.js
      import js.JSConverters._

      def toLiteralMacro(t: $tpeSCProps): js.Dictionary[js.Any] = {
          val res = js.Dictionary.empty[js.Any]
          $scPropFieldsExpansion
          $simpleFieldsExpansion
          res
      }
    }
  """
    }
//     println(res)
    res
  }
}