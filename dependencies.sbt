libraryDependencies ++= {
  object Version {
    val ElasticSearch = "0.20.5"
  }
  Seq(
    "org.elasticsearch" % "elasticsearch" % Version.ElasticSearch
  )
}
