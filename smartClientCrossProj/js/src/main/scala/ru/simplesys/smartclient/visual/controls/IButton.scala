package ru.simplesys
package smartclient
package visual
package controls

import scala.scalajs.js
import macrojs._

trait IButton extends Canvas {
  //def title: String = js.native
  def getTitle(): String = js.native
  def setTitle(s: String): Unit = js.native
}


class IButtonProps extends CanvasProps {

  override type ClickHandler <: IButton
  var title = noSCProp[String]
}

object IButton extends SCApply[IButton, IButtonProps]


