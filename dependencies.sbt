libraryDependencies ++= {
  object Version {
    val ElasticSearch = "1.5.0"
    val Scalatest  = "2.2.4"
    val Scalacheck = "1.12.2"
  }
  List(
    "org.elasticsearch"  % "elasticsearch" % Version.ElasticSearch,
    "org.scalatest"     %% "scalatest"     % Version.Scalatest       % "test",
    "org.scalacheck"    %% "scalacheck"    % Version.Scalacheck      % "test"
  )
}
