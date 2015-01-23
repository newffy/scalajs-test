package ru.simplesys
package smartclient
package nonvisual
package types

import cmntypes._

trait SCType[T] {
  def name: String
}

trait SCTypeCustom[T] extends SCType[T] with SCTypeProps[T]




