import aether.Aether._

publishTo <<= version { (v: String) =>
  val corporateRepo = "http://toucan.simplesys.lan/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at corporateRepo + "artifactory/libs-snapshot-local")
  else
    Some("releases"  at corporateRepo + "artifactory/libs-release-local")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

aetherSettings

aetherPublishSettings


