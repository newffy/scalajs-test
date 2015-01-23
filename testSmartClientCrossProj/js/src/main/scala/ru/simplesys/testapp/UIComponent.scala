package ru.simplesys
package testapp

import ru.simplesys.macrojs._
import smartclient.visual.layouts.{VLayoutProps, VLayout}
import smartclient.visual._
import smartclient._
import scala.scalajs.js

trait MyComplexComponent extends VLayout {

}

class MyComplexComponentProps extends VLayoutProps {
  override type ClickHandler <: MyComplexComponent
}

object MyComplexComponent extends SCApply[MyComplexComponent, MyComplexComponentProps](Some("VLayout"))