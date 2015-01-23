package ru.simplesys
package macrojs

import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context

import cmntypes._

trait CreatorMacro[P <: SCProps] {
  def create: P
}

object CreatorMacro {
  implicit def materializeCreatorMacro[P <: SCProps]: CreatorMacro[P] = macro materializeCreatorMacroImpl[P]

  def materializeCreatorMacroImpl[P <: SCProps : c.WeakTypeTag](c: Context): c.Expr[P] = {
    import c.universe._
    val typeP = weakTypeOf[P]


    val res = c.Expr[P] {
      q"""
          new CreatorMacro[$typeP] {
            def create: $typeP = new $typeP {}
          }
      """
    }
//    println(res)
    res
  }
}
