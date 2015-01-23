package ru.simplesys
package smartclient
package nonvisual
package databinding

import macrojs._

import cmntypes._
import smartclient.nonvisual.common.SCClass

import scala.scalajs.js

trait DataSource extends SCClass {
  def dataURL: String = js.native
  def fields: js.Array[DataSourceField[_]] = js.native
}

class DataSourceProps extends SCProps {
  var dataURL = noSCProp[String]
  //here could be DataSourceField too
  var fields = noSCProp[Either[Seq[DataSourceField[_]], Seq[DataSourceFieldProps[_]]]]
}

object DataSource extends SCApply[DataSource, DataSourceProps]


