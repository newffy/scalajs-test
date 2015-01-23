package scala.scalajs.js.annotation

class JSBracketAccess extends scala.annotation.StaticAnnotation

class JSExport extends scala.annotation.StaticAnnotation {
  def this(name: String) = this()
}

class JSExportAll extends scala.annotation.StaticAnnotation

class JSExportDescendentClasses extends scala.annotation.StaticAnnotation

class JSExportDescendentObjects extends scala.annotation.StaticAnnotation

class JSExportNamed extends scala.annotation.StaticAnnotation {
  def this(name: String) = this()
}

class JSName(name: String) extends scala.annotation.StaticAnnotation

class RawJSType extends scala.annotation.StaticAnnotation

