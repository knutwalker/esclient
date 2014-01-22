libraryDependencies ++= {
  object Version {
    val ElasticSearch = "1.0.0.RC1"
    val Scalatest  = "2.0"
    val Scalacheck = "1.10.1"
  }
  Seq(
    "org.elasticsearch"  % "elasticsearch" % Version.ElasticSearch,
    "org.scalatest"     %% "scalatest"     % Version.Scalatest       % "test",
    "org.scalacheck"    %% "scalacheck"    % Version.Scalacheck      % "test"
  )
}
