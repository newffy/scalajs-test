package ru.simplesys
package smartclient
package nonvisual
package types

import scala.scalajs.js
import cmntypes._
import macrojs._

import smartclient.nonvisual.common.SCClass

trait SimpleType extends SCClass {
  def name: String = js.native
  def inheritsFrom: String = js.native
}


object SimpleType extends SCApply[SimpleType, SCTypeProps[_]]

trait SCTypeProps[T] extends SCProps {
  def name: String
  var inheritsFrom = noSCProp[SCType[T]]
}
