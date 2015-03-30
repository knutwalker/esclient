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

    libraryDependencies += "de.knutwalker" %% "esclient" % "1.5.0"

Note that the esclient versioning is aligned with the elastic search version.
This is release based on Elasticsearch version 1.5.0

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
