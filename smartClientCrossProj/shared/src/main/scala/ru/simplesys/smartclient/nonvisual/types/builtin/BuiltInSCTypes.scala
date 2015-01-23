package ru.simplesys
package smartclient
package nonvisual
package types
package builtin

sealed trait BuiltInSCType[T] extends SCType[T]

case object SCTextType extends BuiltInSCType[String] {
  override def name: String = "text"
}

case object SCBooleanType extends BuiltInSCType[Boolean] {
  override def name: String = "boolean"
}

case object SCIntegerType extends BuiltInSCType[Int] {
  override def name: String = "integer"
}

case object SCDoubleType extends BuiltInSCType[Double] {
  override def name: String = "float"
}
