package ru.simplesys.build

import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object CommonSettings {
  val scalaVersion = "2.11.5"
  val organization = "ru.simplesys"
  val scalacOptions = Seq(
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-deprecation",
    "-unchecked")

  object versions {
    val akkaVersion = "2.3.7"
    val akkaHttpVersion = "0.11"
    val kamonVersion = "0.3.5"
    val sprayVersion = "1.3.2"
    val scalaAsyncVersion = "0.9.2"
    val scalajsDOMVersion = "0.7.0"
    val scalaRxVersion = "0.2.7-RC2"
    val smartclientVersion = "10.0-v20141114"


    val scalaTestVersion = "2.2.2"
  }

  object cmnDependencies {
    val scalaAsync = "org.scala-lang.modules" %% "scala-async" % versions.scalaAsyncVersion
  }

  object jsDependencies {
    val scalaRx = "com.scalarx" %%%! "scalarx" % versions.scalaRxVersion
    val scalajsDOM = "org.scala-js" %%%! "scalajs-dom" % versions.scalajsDOMVersion
//    val smartClient = "com.simplesys" % "smartclient-js" % versions.smartclientVersion
  }

  object jvmDependencies {
    val scalaRx = "com.scalarx" %% "scalarx" % versions.scalaRxVersion
    val scalaTest = "org.scalatest" %% "scalatest" % versions.scalaTestVersion
  }

}
