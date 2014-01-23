# Elastic Search Client for Scala

The Scala client is a thin wrapper around the Java client. It features:

 - Asynchronous interface
 - Based on Scala 2.10 Futures
 - Single dispatch method (execute)
 - Type-safe Request / Response pairs using 'magnet' pattern

The signature of the execute method (slightly simplified):

```scala
implicit class ESClient(client : Client) extends AnyVal {
  def execute[Request, Response](request: Request): Future[Response]
}
```

### Usage

    resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

    libraryDependencies ++= Seq(
      "org.elasticsearch" % "elasticsearch" % "1.0.0.RC1",
      "de.knutwalker" %% "esclient" % "1.0.0.RC1-SNAPSHOT"
    )

Note that the esclient versioning is aligned with the elastic search version.
This is release based on Elasticsearch version 1.0.0.RC1
Elasticsearch is only defined as `provided`, which means you have to pull the correct
version in yourself.

### Sample

```scala
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.node.NodeBuilder.nodeBuilder
import de.knutwalker.esclient.ESClient

object TestES extends App {

  val client = nodeBuilder.node.client

  val response : Future[IndexResponse] =
    client.execute(new IndexRequest)

  println("Document id: " + Await.result(response, Duration.Inf).id)
}
```


### License

This software is released under the Apache License, Version 2.0

http://www.apache.org/licenses/LICENSE-2.0.html
