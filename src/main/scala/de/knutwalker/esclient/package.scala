package de.knutwalker

import scala.concurrent.Future
import org.elasticsearch.client.Client
import org.elasticsearch.action.{ActionRequest, ActionResponse}

package object esclient {

  implicit class ESClient(val javaClient: Client) extends AnyVal {
    def execute[Req <: ActionRequest[Req], Resp <: ActionResponse](request: Req)(implicit action: ActionMagnet[Req, Resp]): Future[Resp] =
      action.execute(javaClient, request)
  }
}
