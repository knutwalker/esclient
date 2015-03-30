import com.typesafe.sbt.pgp.PgpKeys._
import sbtrelease._
import sbtrelease.ReleasePlugin._
import sbtrelease.ReleasePlugin.ReleaseKeys._
import sbtrelease.ReleaseStateTransformations._
import xerial.sbt.Sonatype.SonatypeKeys._

releaseSettings

licenses += "The Apache Software Licence, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
scmInfo := Some(ScmInfo(url("https://github.com/knutwalker/esclient"), "scm:git:https://github.com/knutwalker/esclient.git", Some("scm:git:ssh://git@github.com:knutwalker/esclient.git")))

pomExtra :=
  <developers>
    <developer>
      <id>ruudditerwich</id>
      <name>Ruud Diterwich</name>
      <url>https://github.com/rditerwich</url>
    </developer>
    <developer>
      <id>knutwalker</id>
      <name>Paul Horn</name>
      <url>http://knutwalker.de/</url>
    </developer>
  </developers>

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }
SonatypeKeys.profileName := "knutwalker"

tagComment <<= (version in ThisBuild) map (v => s"Release version $v")
commitMessage <<= (version in ThisBuild) map (v => s"Set version to $v")
versionBump := sbtrelease.Version.Bump.Bugfix

releaseProcess := List[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  setReleaseVersion,
  runClean,
  runTest,
  commitReleaseVersion,
  tagRelease,
  publishSignedArtifacts,
  releaseToCentral,
  setNextVersion,
  commitNextVersion,
  pushChanges,
  publishArtifacts
)

lazy val publishSignedArtifacts = publishArtifacts.copy(
  action = { st: State =>
    val extracted = Project.extract(st)
    val ref = extracted.get(Keys.thisProjectRef)
    extracted.runAggregated(publishSigned in Global in ref, st)
  },
  enableCrossBuild = true
)

lazy val releaseToCentral = ReleaseStep(
  action = { st: State =>
    val extracted = Project.extract(st)
    val ref = extracted.get(Keys.thisProjectRef)
    extracted.runAggregated(sonatypeReleaseAll in Global in ref, st)
  },
  enableCrossBuild = true
)
