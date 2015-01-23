addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.0-RC2")

addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.2")

//addSbtPlugin("com.lihaoyi" % "workbench" % "0.2.3")

addSbtPlugin("ru.simplesys" % "merge-js" % "1.0.0-SNAPSHOT")

scalacOptions := Seq(
  "-feature",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-language:existentials",
  "-deprecation",
  "-unchecked")


//offline := true