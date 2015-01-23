package ru.simplesys.build
import sbt._
import sbt.Keys._

import spray.revolver.RevolverPlugin.Revolver
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._


//trait RootProject {
//  self: Build with MacroProject with SmartClientCrossProj =>
//
//
//}

object MyBuild extends Build with
  MacroProject with
  SmartClientCrossProj with
  ScalaJSStubsProj with
  CommonTypesProj with
  CommonDomainsProj with
  TestSmartClientCrossProj {
  override def settings: Seq[Def.Setting[_]] = super.settings ++ Seq(
    scalaVersion := CommonSettings.scalaVersion,
    scalacOptions := CommonSettings.scalacOptions,
    organization := CommonSettings.organization

  )

  lazy val root = Project("core-libs-scalajs", file(".")) settings(
    Revolver.settings ++ Seq(
      //scalacOptions += "-Xlog-implicits",
      persistLauncher := true,
      libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-actor" % CommonSettings.versions.akkaVersion,
        "com.typesafe.akka" %% "akka-slf4j" % CommonSettings.versions.akkaVersion,
        "io.spray"          %%  "spray-can"     % CommonSettings.versions.sprayVersion,
        "io.spray"          %%  "spray-routing" % CommonSettings.versions.sprayVersion//,
//        CommonSettings.jsDependencies.smartClient
      ),
      mainClass in Revolver.reStart := Some("ru.simplesys.server.Boot")

    ): _*
    ) dependsOn(testSmartClientJVM) aggregate(
    commonTypesJS,
    commonTypesJVM,
    macroSub,
    smartClientJS,
    smartClientJVM,
    scalaJSStubJVM,
    commonDomainJS,
    commonDomainJVM,
    testSmartClientJVM,
    testSmartClientJS)

}

