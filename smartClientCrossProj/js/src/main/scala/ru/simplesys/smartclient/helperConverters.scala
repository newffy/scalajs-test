package ru.simplesys
package smartclient

//import macrojs._
import cmntypes._

object helperConverters {
  //implicit def toSCPropOpt[A](x: A): SCPropOpt[A] = SCPropVal(x)
  @inline def noSCProp[A]: SCPropOpt[A] = NoSCPropVal

  //implicit def toPropOpt[A](x: A): PropOpt[A] = PropVal(x)
  @inline def noProp[A]: PropOpt[A] = NoPropVal
}
