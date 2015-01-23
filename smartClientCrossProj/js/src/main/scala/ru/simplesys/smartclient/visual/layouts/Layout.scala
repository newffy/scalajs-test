package ru.simplesys
package smartclient
package visual
package layouts

import macrojs._
import cmntypes._
import visual._

import scala.collection.mutable
import scala.scalajs.js

// Layout
trait Layout extends Canvas {
  def vertical: Boolean = js.native

  //def members
  def getMembers(): js.Array[Canvas] = js.native
  def setMembers(m: js.Array[Canvas]): Unit = js.native
}


trait LayoutProps extends CanvasProps {
  override type ClickHandler <: Layout
  var vertical = noSCProp[Boolean]
  var members = noSCProp[Seq[Canvas]]
}

object Layout extends SCApply[Layout, LayoutProps]
// Layout

// HLayout
trait HLayout extends Layout

class HLayoutProps extends LayoutProps {
  override type ClickHandler <: HLayout
}

object HLayout extends SCApply[HLayout, HLayoutProps]
// HLayout

// VLayout
trait VLayout extends Layout

class VLayoutProps extends LayoutProps {
  override type ClickHandler <: VLayout
}

object VLayout extends SCApply[VLayout, VLayoutProps]
// VLayout

