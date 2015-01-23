package ru.simplesys.build


import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport._
import sbt.Keys._
import sbt._

trait CommonDomainsProj {
  self: Build with MacroProject with SmartClientCrossProj =>

  lazy val commonDomainsCrossProj = crossProject.dependsOn(smartClientCrossProj).
    settings(
      name := "common-domains",
      version := "1.0.0-SNAPSHOT"
    ).
    jvmSettings().
    jsSettings(
      libraryDependencies ++= Seq(
//        CommonSettings.jsDependencies.smartClient % "provided"
      )
  ).jsConfigure(x => x.dependsOn(macroSub))

  // Needed, so sbt finds the projects
  lazy val commonDomainJVM = commonDomainsCrossProj.jvm
  lazy val commonDomainJS = commonDomainsCrossProj.js
}
