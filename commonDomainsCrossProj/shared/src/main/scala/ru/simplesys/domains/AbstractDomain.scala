package ru.simplesys
package domains

import smartclient.nonvisual.types.SCTypeCustom

trait DataType[S, JS] {
  self: SCTypeCustom[JS] =>
}

trait DataTypeUni[T] extends DataType[T, T] {
  self: SCTypeCustom[T] =>
}

trait DataTypeConv[S, JS] {
  self: SCTypeCustom[JS] =>
  def toJS: S => JS
  def fromJS: JS => S
}
