package de.knutwalker.esclient

import scala.concurrent.{Promise, Future}

import org.elasticsearch.client.Client
import org.elasticsearch.action.{ActionResponse, ActionRequest, ActionListener}


sealed trait ActionMagnet[Request, Response] {
  def execute(javaClient: Client, request: Request): Future[Response]
}

object ActionMagnet {

  implicit val clusterHealthAction = magnet(_.admin.cluster.health)
  implicit val nodesHotThreadsAction = magnet(_.admin.cluster.nodesHotThreads)
  implicit val nodesInfoAction = magnet(_.admin.cluster.nodesInfo)
  implicit val nodesRestartAction = magnet(_.admin.cluster.nodesRestart)
  implicit val nodesShutdownAction = magnet(_.admin.cluster.nodesShutdown)
  implicit val nodesStatsAction = magnet(_.admin.cluster.nodesStats)
  implicit val deleteRepositoryAction = magnet(_.admin.cluster.deleteRepository)
  implicit val getRepositoriesAction = magnet(_.admin.cluster.getRepositories)
  implicit val putRepositoryAction = magnet(_.admin.cluster.putRepository)
  implicit val clusterRerouteAction = magnet(_.admin.cluster.reroute)
  implicit val clusterUpdateSettingsAction = magnet(_.admin.cluster.updateSettings)
  implicit val clusterSearchShardsAction = magnet(_.admin.cluster.searchShards)
  implicit val createSnapshotAction = magnet(_.admin.cluster.createSnapshot)
  implicit val deleteSnapshotAction = magnet(_.admin.cluster.deleteSnapshot)
  implicit val getSnapshotsAction = magnet(_.admin.cluster.getSnapshots)
  implicit val restoreSnapshotAction = magnet(_.admin.cluster.restoreSnapshot)
  implicit val clusterStateAction = magnet(_.admin.cluster.state)
  implicit val clusterStatsAction = magnet(_.admin.cluster.clusterStats)
  implicit val pendingClusterTasksAction = magnet(_.admin.cluster.pendingClusterTasks)

  implicit val aliasesExistAction = magnet(_.admin.indices.aliasesExist)
  implicit val getAliasesAction = magnet(_.admin.indices.getAliases)
  implicit val indicesAliasesAction = magnet(_.admin.indices.aliases)
  implicit val analyzeAction = magnet(_.admin.indices.analyze)
  implicit val clearIndicesCacheAction = magnet(_.admin.indices.clearCache)
  implicit val closeIndexAction = magnet(_.admin.indices.close)
  implicit val createIndexAction = magnet(_.admin.indices.create)
  implicit val deleteIndexAction = magnet(_.admin.indices.delete)
  implicit val indicesExistsAction = magnet(_.admin.indices.exists)
  implicit val typesExistsAction = magnet(_.admin.indices.typesExists)
  implicit val flushAction = magnet(_.admin.indices.flush)
  implicit val deleteMappingAction = magnet(_.admin.indices.deleteMapping)
  implicit val getFieldMappingsAction = magnet(_.admin.indices.getFieldMappings)
  implicit val getMappingsAction = magnet(_.admin.indices.getMappings)
  implicit val putMappingAction = magnet(_.admin.indices.putMapping)
  implicit val openIndexAction = magnet(_.admin.indices.open)
  implicit val optimizeAction = magnet(_.admin.indices.optimize)
  implicit val refreshAction = magnet(_.admin.indices.refresh)
  implicit val indicesSegmentsAction = magnet(_.admin.indices.segments)
  implicit val getSettingsAction = magnet(_.admin.indices.getSettings)
  implicit val updateSettingsAction = magnet(_.admin.indices.updateSettings)
  implicit val indicesStatsAction = magnet(_.admin.indices.stats)
  @deprecated("Use the recovery API instead. See JavaDoc for more", "1.0.0")
  implicit val indicesStatusAction = magnet(_.admin.indices.status)
  implicit val deleteIndexTemplateAction = magnet(_.admin.indices.deleteTemplate)
  implicit val getIndexTemplatesAction = magnet(_.admin.indices.getTemplates)
  implicit val putIndexTemplateAction = magnet(_.admin.indices.putTemplate)
  implicit val validateQueryAction = magnet(_.admin.indices.validateQuery)
  implicit val deleteWarmerAction = magnet(_.admin.indices.deleteWarmer)
  implicit val getWarmersAction = magnet(_.admin.indices.getWarmers)
  implicit val putWarmerAction = magnet(_.admin.indices.putWarmer)
  implicit val bulkAction = magnet(_.bulk)
  implicit val clearScrollAction = magnet(_.clearScroll)
  implicit val countAction = magnet(_.count)
  implicit val deleteAction = magnet(_.delete)
  implicit val deleteByQueryAction = magnet(_.deleteByQuery)
  implicit val explainAction = magnet(_.explain)
  implicit val getAction = magnet(_.get)
  implicit val indexAction = magnet(_.index)
  implicit val moreLikeThisAction = magnet(_.moreLikeThis)
  implicit val multiGetAction = magnet(_.multiGet)
  implicit val multiPercolateAction = magnet(_.multiPercolate)
  implicit val multiSearchAction = magnet(_.multiSearch)
  implicit val multiTermVectorsAction = magnet(_.multiTermVectors)
  implicit val percolateAction = magnet(_.percolate)
  implicit val searchAction = magnet(_.search)
  implicit val searchScrollAction = magnet(_.searchScroll)
  implicit val suggestAction = magnet(_.suggest)
  implicit val termVectorAction = magnet(_.termVector)
  implicit val updateAction = magnet(_.update)

  private[this] def magnet[Req <: ActionRequest[Req], Resp <: ActionResponse](action: Client => (Req, ActionListener[Resp]) => Unit) =
    new ActionMagnet[Req, Resp] {
      def execute(javaClient: Client, request: Req): Future[Resp] = {
        val promise = Promise[Resp]()
        action(javaClient)(request, new ActionFuture[Resp](promise))
        promise.future
      }
    }

  private[this] final class ActionFuture[A](promise: Promise[A]) extends ActionListener[A] {
    def onFailure(e: Throwable): Unit = promise.failure(e)
    def onResponse(response: A): Unit = promise.trySuccess(response)
  }
}
