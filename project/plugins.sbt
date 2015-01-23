addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.0-RC2")

addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.2")

scalacOptions := Seq(
  "-feature",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-language:existentials",
  "-deprecation",
  "-unchecked")


