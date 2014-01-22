name := """esclient"""

organization := "de.knutwalker"

organizationName := "knutwalker"

organizationHomepage := Some(url("https://knutwalker.de/"))

description := """Slim Elasticsearch client for scala"""

homepage := Some(url("http://knutwalker.de/esclient/"))

startYear := Some(2014)

scalaVersion := "2.10.3"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

EclipseKeys.withSource := true

