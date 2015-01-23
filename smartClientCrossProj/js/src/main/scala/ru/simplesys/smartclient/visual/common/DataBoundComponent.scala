package ru.simplesys
package smartclient
package visual
package common

import cmntypes._
import smartclient.nonvisual.databinding.{DataSourceField, DataSource}
import smartclient.nonvisual.common.SCClass

import scala.scalajs.js

trait DataBoundComponent extends SCClass {
  def dataSource: DataSource = js.native
  def setDataSource(ds: DataSource): Unit = js.native
  def setDataSource(ds: DataSource, fields: js.Array[DataSourceField[_]]): Unit = js.native
}

trait DataBoundComponentProps extends SCProps {
  var dataSource = noSCProp[DataSource]
}