package de.knutwalker

import scala.concurrent.Future
import org.elasticsearch.client.Client
import org.elasticsearch.action.{ActionRequest, ActionResponse}
import de.knutwalker.esclient.impl.ActionMagnet

package object esclient {

  implicit class ESClient(val javaClient: Client) extends AnyVal {
    def execute[Request <: ActionRequest[Request], Response <: ActionResponse](request: Request)(implicit action: ActionMagnet[Request, Response]): Future[Response] =
      action.execute(javaClient, request)
  }
}
