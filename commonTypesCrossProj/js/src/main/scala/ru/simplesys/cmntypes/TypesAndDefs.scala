package ru.simplesys.cmntypes

import scala.scalajs.js

trait SCProps {
  implicit def toSCPropOpt[A](x: A): SCPropOpt[A] = SCPropVal(x)
  @inline def noSCProp[A]: SCPropOpt[A] = NoSCPropVal

  implicit def toPropOpt[A](x: A): PropOpt[A] = PropVal(x)
  @inline def noProp[A]: PropOpt[A] = NoPropVal
}


object SCPropOpt {
  implicit def scPropOpt2jsAny[A](value: SCPropOpt[A])(implicit ev: A => js.Any): js.Any =
    value.map(ev).asInstanceOf[js.Any]
}

trait SCPropOpt[+A] {
  def isEmpty: Boolean
  def isDefined: Boolean = !isEmpty
  def get: A
  @inline final def map[B](f: A => B): SCPropOpt[B] =
    if (isEmpty) NoSCPropVal else SCPropVal(f(this.get))

  @inline final def flatMap[B](f: A => Option[B]): Option[B] =
    if (isEmpty) None else f(this.get)

  @inline final def foreach[U](f: A => U) {
    if (!isEmpty) f(this.get)
  }
}

case class SCPropVal[+A](value: A) extends SCPropOpt[A] {
  override def get: A = value
  def isEmpty = false
}

case object NoSCPropVal extends SCPropOpt[Nothing] {
  override def get: Nothing = throw new NoSuchElementException("NoSCPropVal.get")
  def isEmpty = true
}

trait PropOpt[+A] {
  def isEmpty: Boolean
  def isDefined: Boolean = !isEmpty
  def get: A
  @inline final def map[B](f: A => B): PropOpt[B] =
    if (isEmpty) NoPropVal else PropVal(f(this.get))

  @inline final def flatMap[B](f: A => Option[B]): Option[B] =
    if (isEmpty) None else f(this.get)

  @inline final def foreach[U](f: A => U) {
    if (!isEmpty) f(this.get)
  }

  def toSCPropOpt: SCPropOpt[A] = if (isEmpty) NoSCPropVal else SCPropVal(this.get)
}

case class PropVal[+A](value: A) extends PropOpt[A] {
  override def get: A = value
  def isEmpty = false

}

case object NoPropVal extends PropOpt[Nothing] {
  override def get: Nothing = throw new NoSuchElementException("NoPropVal.get")
  def isEmpty = true
}

