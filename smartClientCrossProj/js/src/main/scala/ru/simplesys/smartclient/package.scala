package ru.simplesys

import macrojs._
import cmntypes._

import scala.scalajs.js

package object smartclient {
  implicit class ToJSLiteral[P <: SCProps](p: P)(implicit toLiteral: ToLiteralMacro[P]) {
    def toJSLiteral: js.Dictionary[js.Any] = toLiteral.toLiteralMacro(p)
  }

  implicit class ToSCPropOpt[A](x: A) {
    def sc: SCPropOpt[A] = SCPropVal(x)
  }
}
