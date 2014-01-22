libraryDependencies ++= {
  object Version {
    val ElasticSearch = "1.0.0.RC1"
  }
  Seq(
    "org.elasticsearch" % "elasticsearch" % Version.ElasticSearch
  )
}
