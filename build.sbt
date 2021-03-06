name := """esclient"""

organization := "de.knutwalker"
organizationName := "knutwalker"
organizationHomepage := Some(url("https://knutwalker.de/"))
homepage := Some(url("http://knutwalker.de/esclient/"))

description := """Slim Elasticsearch client for scala"""
startYear := Some(2014)

scalaVersion := "2.11.7"
crossScalaVersions := "2.11.7" :: "2.10.5" :: Nil
scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)
