package ru.simplesys
package testapp

import scala.scalajs.js
import ru.simplesys.macrojs._

import smartclient.SCApply
import smartclient.visual._
import smartclient.visual.controls._
import smartclient._

class SCButton extends IButton {
  var clickCount: Int = js.native
}


class SCButtonProps extends IButtonProps {

  override type ClickHandler <: SCButton
  var clickCount = 0
  click = SCButton.clickHandler
}

object SCButton extends SCApply[SCButton, SCButtonProps](Some("IButton")) {
  val clickHandler = {(that: SCButton) =>
    import that._
    clickCount += 1
    setTitle(s"clicked!!! ${clickCount} times getLeft is ${getLeft()}, getRight is ${getRight()}, getWidth is ${getWidth()}, getHeight is ${getHeight()}")
    println(s"${getTitle()}")
    true
  }: js.ThisFunction0[SCButton, Boolean]


}

