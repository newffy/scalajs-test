package ru.simplesys
package smartclient


import macrojs._
import cmntypes._


import scala.reflect.ClassTag
import scala.scalajs.js


abstract class SCApply[T <: js.Object, P <: SCProps](scClassNameOpt: Option[String] = None)(implicit ct: ClassTag[T], toLiteral: ToLiteralMacro[P], cp: ClassTag[P]/*, creator: CreatorMacro[P]*/) {
  val scClassName = scClassNameOpt.getOrElse({
    //val ct = implicitly[ClassTag[T]]
    ct.runtimeClass.getSimpleName
  })

  //val toLiteral = implicitly[ToLiteralMacro[P]]

  //protected val cp = implicitly[ClassTag[P]]

  def apply/*[Q <: P : ToLiteralMacro]*/(props: P): T = {
    js.Dynamic.global.isc.selectDynamic(scClassName).create(toLiteral.toLiteralMacro(props)).asInstanceOf[T]
  }

//  def create(modifierFunc: P => Unit ): T = {
//    val blank = creator.create
//    modifierFunc(blank)
//    apply(blank)
//  }
}
