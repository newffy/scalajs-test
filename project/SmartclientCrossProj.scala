package ru.simplesys.build

import sbt._
import Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport._

trait SmartClientCrossProj {
  self: Build with MacroProject with CommonTypesProj =>

  lazy val smartClientCrossProj = crossProject.dependsOn(commonTypesCrossProj).
    settings(
      name := "smartclient-wrapper",
      version := "1.0.0-SNAPSHOT",
      libraryDependencies ++= {
        Seq(
          CommonSettings.cmnDependencies.scalaAsync
        )
      }
    ).
    jvmSettings(
    libraryDependencies ++= {
      Seq(
        CommonSettings.jvmDependencies.scalaTest % "test"
      )
    }).
    jsSettings(
      scalacOptions += "-Xlog-implicits",
    //persistLauncher := true,

    libraryDependencies ++= Seq(
        CommonSettings.jsDependencies.smartClient % "provided"
      )
    //jsDependencies += "org.example" %% "js-thing" % "0.1" / "foo.js"
    ).jsConfigure(x => x.dependsOn(macroSub))

  // Needed, so sbt finds the projects
  lazy val smartClientJVM = smartClientCrossProj.jvm
  lazy val smartClientJS = smartClientCrossProj.js

}
