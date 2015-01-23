//import spray.revolver.RevolverPlugin.Revolver
//import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
//import com.simplesys.mergewebapp.MergeWebappPlugin._


//scalaVersion := CommonSettings.scalaVersion
//
//scalacOptions := CommonSettings.scalacOptions

//lazy val root = Project("core-libs-scalajs", file(".")) dependsOn(smartClientJVM) aggregate(macroSub, smartClientJS, smartClientJVM)

//lazy val macroSub = Project("macrojs", file("macrojs")) settings(
//  scalaVersion := CommonSettings.scalaVersion,
//  libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _)//,
//  //scalaVersion <<= scalaVersion
//  )

//lazy val smartClientCrossProj = crossProject.
//  settings(
//    name := "smartclient-wrapper",
//    organization := CommonSettings.organization,
//    version := "1.0.0-SNAPSHOT",
//    scalaVersion := CommonSettings.scalaVersion,
//    scalacOptions := CommonSettings.scalacOptions,
//    libraryDependencies ++= {
//      Seq(
//        "org.scala-lang.modules" %% "scala-async" % "0.9.2",
//        "com.scalarx" %% "scalarx" % CommonSettings.versions.scalaRxVersion,
//        "com.scalarx" %%% "scalarx" % CommonSettings.versions.scalaRxVersion
//      )
//    }
//  ).
//  jvmSettings(Seq(
//  libraryDependencies ++= {
//    Seq(
//        "org.scalatest" %% "scalatest" % CommonSettings.versions.scalaTestVersion % "test"
//      )
//    }):_*
//  ).
//  jsSettings(mergeWebappSettings ++ Seq(
//    //scalacOptions += "-Xlog-implicits",
//    persistLauncher := true,
//    mergeMapping in MergeWebappConfig := Seq(
//      ("com.simplesys", "smartclient-js") -> Seq(
//        Seq("isomorphic") -> Some(Seq("webapp", "javascript", "isomorphic"))
//      )
//      ),
//    currentProjectGenerationDirPath in MergeWebappConfig <<= (sourceDirectory in Compile) {
//      _ / "webapp" / "javascript" / "generated"
//    },
//    currentProjectDevelopedDirPath in MergeWebappConfig <<= (sourceDirectory in Compile) {
//      _ / "webapp" / "javascript" / "developed"
//    },
//
//      libraryDependencies ++= {
//      Seq(
//        "org.scala-js" %%% "scalajs-dom" % "0.7.0",
//        "com.simplesys" % "smartclient-js" % CommonSettings.versions.smartclientVersion
//      )
//    }
//    //jsDependencies += "org.example" %% "js-thing" % "0.1" / "foo.js"
//    ):_*
//  ).jsConfigure(x => x.dependsOn(macroSub))
//
//// Needed, so sbt finds the projects
//lazy val smartClientJVM = smartClientCrossProj.jvm
//lazy val smartClientJS = smartClientCrossProj.js

//lazy val p2 = crossProject.crossType(CrossType.Full).dependsOn(p1 % "smartclient-wrapper")

// Needed, so sbt finds the projects
//lazy val p2JVM = p2.jvm
//lazy val p2JS = p2.js


//libraryDependencies ++= Seq(
//    "com.typesafe.akka" %% "akka-actor" % CommonSettings.versions.akkaVersion,
//    "com.typesafe.akka" %% "akka-slf4j" % CommonSettings.versions.akkaVersion,
//    "io.spray"          %%  "spray-can"     % CommonSettings.versions.sprayVersion,
//    "io.spray"          %%  "spray-routing" % CommonSettings.versions.sprayVersion
//  )


//Revolver.settings
//
//mainClass in Revolver.reStart := Some("ru.simplesys.server.Boot")

