package ru.simplesys.build

import sbt._
import Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin

trait MacroProject {
  self: Build with CommonTypesProj =>
  lazy val macroSub = Project("macrojs", file("macrojs")).settings(
    name := "macrojs",
    version := "1.0.0-SNAPSHOT",
    libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _)
    ).dependsOn(commonTypesJS).enablePlugins(ScalaJSPlugin)
}
