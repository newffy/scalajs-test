package ru.simplesys.scalajs

import org.scalatest.{Matchers, FunSuite}

trait One
trait Two extends One
trait Three extends Two

class OneProps {
  type X <: One
  var t: Function1[X, Boolean] = ???
}

class TwoProps extends OneProps {
  type X <: Two
  val r: (X) => Boolean = t
}

class ThreeProps extends TwoProps {
  type X <: Three
}


class TestTry extends FunSuite with Matchers {
   test("simple test") {
     val x = new ThreeProps()
   }
}
