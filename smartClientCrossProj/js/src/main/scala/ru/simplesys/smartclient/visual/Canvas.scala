package ru.simplesys
package smartclient
package visual

import macrojs._
import cmntypes._
import smartclient.nonvisual.common.SCClass

import scala.scalajs.js


// coordinates and sizes specifications
trait SizeSpec {
  def toJS: js.Any
}

object SizeSpec {
  @inline implicit def sizeSpecToJS(p: SizeSpec): js.Any = p.toJS
}

trait PointSpec {
  def toJS: js.Any
}

object PointSpec {
  @inline implicit def pointSpecToJS(p: PointSpec): js.Any = p.toJS
}

case class PixelPointSpec(pos: Int) extends PointSpec with SizeSpec {
  override def toJS: js.Any = pos
}

case class PctPointSpec(pct: Int) extends PointSpec with SizeSpec {
  override def toJS: js.Any = s"$pct%"
}

case object WildCard extends SizeSpec {
  override def toJS: js.Any = "*"
}
// coordinates and sizes specifications

//Splitter settings
object SplitterSetting {
  def apply(showResizeBar: Boolean, resizeBarTarget: String): SplitterSetting = {
    if (showResizeBar) {
      if (resizeBarTarget == "next") ShowSplitterResizeOther
      else ShowSplitter
    }
    else NoSplitter
  }
}

sealed trait SplitterSetting {
  def toProps: (Boolean, String)
}

case object ShowSplitter extends SplitterSetting {
  override def toProps: (Boolean, String) = (true, null)
}
case object ShowSplitterResizeOther extends SplitterSetting {
  override def toProps: (Boolean, String) = (true, "next")
}
case object NoSplitter extends SplitterSetting {
  override def toProps: (Boolean, String) = (false, null)
}
//Splitter settings

trait Canvas extends SCClass {
  def autoDraw: Boolean = js.native

  //def top: js.Number = js.native
  def getTop(): Int = js.native
  protected def setTop(t: js.Any): Unit = js.native

  //def left: js.Number = js.native
  def getLeft(): Int = js.native
  protected def setLeft(l: js.Any): Unit = js.native

  //def height: js.Number = js.native
  def getHeight(): Int = js.native
  protected def setHeight(h: js.Any): Unit = js.native //Number Or String

  //def width: js.Number = js.native
  def getWidth(): Int = js.native
  protected def setWidth(w: js.Any): Unit = js.native //Number Or String

  def getRight(): Int = js.native
  def setRight(r: Int): Unit = js.native //Number Or String
  //protected def setRight(r: js.Any): Unit = js.native //Number Or String

  def getBottom(): Int = js.native
  def setBottom(b: Int): Unit = js.native

  def click(): Boolean = js.native

  def showResizeBar: Boolean = js.native
  def setShowResizeBar(b: Boolean): Unit = js.native
  protected def resizeBarTarget: String = js.native
}

object Canvas extends SCApply[Canvas, CanvasProps] {
  implicit class ToCanvasExt(c: Canvas) {
    @inline def setHeight(h: SizeSpec) = c.setHeight(h)
    @inline def setWidth(w: SizeSpec) = c.setWidth(w)
    @inline def setTop(t: PointSpec) = c.setTop(t)
    @inline def setLeft(l: PointSpec) = c.setLeft(l)
    //def setRight(r: PointSpec) = c.setRight(r)
    @inline def getSplitterSetting: SplitterSetting = SplitterSetting(c.showResizeBar, c.resizeBarTarget)
  }
}


trait CanvasProps extends SCProps {
  //import smartclient.helperConverters._
  type ClickHandler <: Canvas
  var left: PointSpec = 0 p
  var top: PointSpec = 0 p
  var width = noSCProp[SizeSpec]
  var height = noSCProp[SizeSpec]
  private var _showResizeBar = noSCProp[Boolean]
  def showResizeBar = _showResizeBar
  private var _resizeBarTarget = noSCProp[String]
  def resizeBarTarget = _resizeBarTarget

  var autoDraw = false
  var click = noSCProp[js.ThisFunction0[ClickHandler, Boolean]]


  private var _splitterSetting = noProp[SplitterSetting]
  def splitterSetting: PropOpt[SplitterSetting] = _splitterSetting
  def splitterSetting_= (splSettOpt: PropOpt[SplitterSetting]): Unit = {
    _splitterSetting = splSettOpt
    val scOpts = _splitterSetting.map(_.toProps).toSCPropOpt
    _showResizeBar = scOpts.map(_._1)
    _resizeBarTarget = scOpts.map(_._2)
  }
}
