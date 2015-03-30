/*
 * Copyright 2014 – 2015 Paul Horn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.knutwalker.esclient

import org.elasticsearch.common.settings.ImmutableSettings

import scala.concurrent._

import org.elasticsearch.node.NodeBuilder.nodeBuilder
import org.elasticsearch.index.query.QueryBuilders._
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, Matchers, FlatSpec}
import org.elasticsearch.node.Node
import org.elasticsearch.client.Client
import org.elasticsearch.action.{ActionResponse, ActionRequest, ActionRequestBuilder}
import org.elasticsearch.action.search.SearchResponse

import java.util.UUID


class ESClientSpec extends FlatSpec with Matchers with BeforeAndAfterAll with BeforeAndAfter {

  var node: Node = _
  var client: Client = _

  private def execute[Request <: ActionRequest[Request], Response <: ActionResponse](rb: ActionRequestBuilder[Request, Response, _, _])(implicit am: ActionMagnet[Request, Response]): Response = {
    val r = rb.request()
    val f = client.execute(r)

    Await.result(f, duration.Duration.Inf)
  }

  override protected def beforeAll(): Unit = {
    node = nodeBuilder()
      .local(true)
      .settings(ImmutableSettings.builder()
        .put("cluster.name", UUID.randomUUID().toString)
        .put("http.enabled", false))
      .node()
    client = node.client()
  }

  override protected def afterAll(): Unit = {
    node.close()
  }

  before {
    client.admin().cluster().prepareHealth("test").setWaitForYellowStatus().execute().actionGet()

    client.prepareDelete("test", "test", "1")
      .execute().actionGet()

    client.prepareIndex("test", "test", "1")
      .setSource("""{"foo": "bar", "bar": "baz"}""")
      .execute().actionGet()

    client.admin().indices().prepareRefresh("test").execute().actionGet()
  }

  "The ESClient" should "provide an implicit conversion for a SearchAction" in {

    val req = client.prepareSearch("test")
      .setQuery(matchAllQuery())
      .addFields("foo", "bar")


    val res = execute(req)
    val hit = res.getHits.getHits.head


    res shouldBe a [SearchResponse]
    res.getHits.getMaxScore shouldBe 1.0F +- 0.001F
    res.getHits.getTotalHits shouldBe 1

    hit.index shouldBe "test"
    hit.`type` shouldBe "test"

    hit.fields.get("foo").getValue[String] shouldBe "bar"
    hit.fields.get("bar").getValue[String] shouldBe "baz"
  }
}
