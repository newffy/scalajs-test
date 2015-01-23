package ru.simplesys.build

import sbt._
import Keys._

trait ScalaJSStubsProj {
  self: Build =>
  lazy val scalaJSStubJVM = Project("scalajs-stubs", file("scalajs-stubs")) settings(
      name := "scalajs-stubs",
      version := "1.0.0-SNAPSHOT"
    )
}
