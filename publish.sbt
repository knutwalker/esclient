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

