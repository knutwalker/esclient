package org.scalastuff.esclient

import scala.concurrent.Future
import scala.concurrent.Promise
import org.elasticsearch.client.Client
import org.elasticsearch.action.Action
import org.elasticsearch.action.ActionListener
import org.elasticsearch.action.ActionRequest
import org.elasticsearch.action.ActionRequestBuilder
import org.elasticsearch.action.ActionResponse

import org.elasticsearch.action.admin.cluster.ClusterAction
import org.elasticsearch.action.admin.cluster.health.ClusterHealthAction
import org.elasticsearch.action.admin.cluster.node.hotthreads.NodesHotThreadsAction
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoAction
import org.elasticsearch.action.admin.cluster.node.restart.NodesRestartAction
import org.elasticsearch.action.admin.cluster.node.shutdown.NodesShutdownAction
import org.elasticsearch.action.admin.cluster.node.stats.NodesStatsAction
import org.elasticsearch.action.admin.cluster.repositories.delete.DeleteRepositoryAction
import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesAction
import org.elasticsearch.action.admin.cluster.repositories.put.PutRepositoryAction
import org.elasticsearch.action.admin.cluster.reroute.ClusterRerouteAction
import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsAction
import org.elasticsearch.action.admin.cluster.shards.ClusterSearchShardsAction
import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotAction
import org.elasticsearch.action.admin.cluster.snapshots.delete.DeleteSnapshotAction
import org.elasticsearch.action.admin.cluster.snapshots.get.GetSnapshotsAction
import org.elasticsearch.action.admin.cluster.snapshots.restore.RestoreSnapshotAction
import org.elasticsearch.action.admin.cluster.state.ClusterStateAction
import org.elasticsearch.action.admin.cluster.stats.ClusterStatsAction
import org.elasticsearch.action.admin.cluster.tasks.PendingClusterTasksAction
import org.elasticsearch.action.admin.indices.alias.exists.AliasesExistAction
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesAction
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesAction
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction
import org.elasticsearch.action.admin.indices.cache.clear.ClearIndicesCacheAction
import org.elasticsearch.action.admin.indices.close.CloseIndexAction
import org.elasticsearch.action.admin.indices.create.CreateIndexAction
import org.elasticsearch.action.admin.indices.delete.DeleteIndexAction
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsAction
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsAction
import org.elasticsearch.action.admin.indices.flush.FlushAction
import org.elasticsearch.action.admin.indices.gateway.snapshot.GatewaySnapshotAction
import org.elasticsearch.action.admin.indices.IndicesAction
import org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingAction
import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsAction
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsAction
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingAction
import org.elasticsearch.action.admin.indices.open.OpenIndexAction
import org.elasticsearch.action.admin.indices.optimize.OptimizeAction
import org.elasticsearch.action.admin.indices.refresh.RefreshAction
import org.elasticsearch.action.admin.indices.segments.IndicesSegmentsAction
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsAction
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsAction
import org.elasticsearch.action.admin.indices.stats.IndicesStatsAction
import org.elasticsearch.action.admin.indices.status.IndicesStatusAction
import org.elasticsearch.action.admin.indices.template.delete.DeleteIndexTemplateAction
import org.elasticsearch.action.admin.indices.template.get.GetIndexTemplatesAction
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateAction
import org.elasticsearch.action.admin.indices.validate.query.ValidateQueryAction
import org.elasticsearch.action.admin.indices.warmer.delete.DeleteWarmerAction
import org.elasticsearch.action.admin.indices.warmer.get.GetWarmersAction
import org.elasticsearch.action.admin.indices.warmer.put.PutWarmerAction
import org.elasticsearch.action.bulk.BulkAction
import org.elasticsearch.action.count.CountAction
import org.elasticsearch.action.delete.DeleteAction
import org.elasticsearch.action.deletebyquery.DeleteByQueryAction
import org.elasticsearch.action.explain.ExplainAction
import org.elasticsearch.action.get.GetAction
import org.elasticsearch.action.get.MultiGetAction
import org.elasticsearch.action.index.IndexAction
import org.elasticsearch.action.mlt.MoreLikeThisAction
import org.elasticsearch.action.percolate.MultiPercolateAction
import org.elasticsearch.action.percolate.PercolateAction
import org.elasticsearch.action.search.MultiSearchAction
//import org.elasticsearch.action.search.ClearScrollAction
import org.elasticsearch.action.search.SearchAction
import org.elasticsearch.action.search.SearchScrollAction
import org.elasticsearch.action.suggest.SuggestAction
import org.elasticsearch.action.termvector.MultiTermVectorsAction
import org.elasticsearch.action.termvector.TermVectorAction
import org.elasticsearch.action.update.UpdateAction


trait ActionMagnet[Request, Response] {
  def execute(javaClient: Client, request: Request): Future[Response]
}

object ActionMagnet {

  implicit val aliasesExistAction = magnet(AliasesExistAction.INSTANCE)
  implicit val analyzeAction = magnet(AnalyzeAction.INSTANCE)
  implicit val bulkAction = magnet(BulkAction.INSTANCE)
  implicit val clearIndicesCacheAction = magnet(ClearIndicesCacheAction.INSTANCE)
//  implicit val clearScrollAction = magnet(ClearScrollAction.INSTANCE)
  implicit val closeIndexAction = magnet(CloseIndexAction.INSTANCE)
  implicit val clusterHealthAction = magnet(ClusterHealthAction.INSTANCE)
  implicit val clusterRerouteAction = magnet(ClusterRerouteAction.INSTANCE)
  implicit val clusterSearchShardsAction = magnet(ClusterSearchShardsAction.INSTANCE)
  implicit val clusterStateAction = magnet(ClusterStateAction.INSTANCE)
  implicit val clusterStatsAction = magnet(ClusterStatsAction.INSTANCE)
  implicit val clusterUpdateSettingsAction = magnet(ClusterUpdateSettingsAction.INSTANCE)
  implicit val countAction = magnet(CountAction.INSTANCE)
  implicit val createIndexAction = magnet(CreateIndexAction.INSTANCE)
  implicit val createSnapshotAction = magnet(CreateSnapshotAction.INSTANCE)
  implicit val deleteAction = magnet(DeleteAction.INSTANCE)
  implicit val deleteByQueryAction = magnet(DeleteByQueryAction.INSTANCE)
  implicit val deleteIndexAction = magnet(DeleteIndexAction.INSTANCE)
  implicit val deleteIndexTemplateAction = magnet(DeleteIndexTemplateAction.INSTANCE)
  implicit val deleteMappingAction = magnet(DeleteMappingAction.INSTANCE)
  implicit val deleteRepositoryAction = magnet(DeleteRepositoryAction.INSTANCE)
  implicit val deleteSnapshotAction = magnet(DeleteSnapshotAction.INSTANCE)
  implicit val deleteWarmerAction = magnet(DeleteWarmerAction.INSTANCE)
  implicit val explainAction = magnet(ExplainAction.INSTANCE)
  implicit val flushAction = magnet(FlushAction.INSTANCE)
  implicit val gatewaySnapshotAction = magnet(GatewaySnapshotAction.INSTANCE)
  implicit val getAction = magnet(GetAction.INSTANCE)
  implicit val getAliasesAction = magnet(GetAliasesAction.INSTANCE)
  implicit val getFieldMappingsAction = magnet(GetFieldMappingsAction.INSTANCE)
  implicit val getIndexTemplatesAction = magnet(GetIndexTemplatesAction.INSTANCE)
  implicit val getMappingsAction = magnet(GetMappingsAction.INSTANCE)
  implicit val getRepositoriesAction = magnet(GetRepositoriesAction.INSTANCE)
  implicit val getSettingsAction = magnet(GetSettingsAction.INSTANCE)
  implicit val getSnapshotsAction = magnet(GetSnapshotsAction.INSTANCE)
  implicit val getWarmersAction = magnet(GetWarmersAction.INSTANCE)
  implicit val indexAction = magnet(IndexAction.INSTANCE)
  implicit val indicesAliasesAction = magnet(IndicesAliasesAction.INSTANCE)
  implicit val indicesExistsAction = magnet(IndicesExistsAction.INSTANCE)
  implicit val indicesSegmentsAction = magnet(IndicesSegmentsAction.INSTANCE)
  implicit val indicesStatsAction = magnet(IndicesStatsAction.INSTANCE)
  implicit val indicesStatusAction = magnet(IndicesStatusAction.INSTANCE)
  implicit val moreLikeThisAction = magnet(MoreLikeThisAction.INSTANCE)
  implicit val multiGetAction = magnet(MultiGetAction.INSTANCE)
  implicit val multiPercolateAction = magnet(MultiPercolateAction.INSTANCE)
  implicit val multiSearchAction = magnet(MultiSearchAction.INSTANCE)
  implicit val multiTermVectorsAction = magnet(MultiTermVectorsAction.INSTANCE)
  implicit val nodesHotThreadsAction = magnet(NodesHotThreadsAction.INSTANCE)
  implicit val nodesInfoAction = magnet(NodesInfoAction.INSTANCE)
  implicit val nodesRestartAction = magnet(NodesRestartAction.INSTANCE)
  implicit val nodesShutdownAction = magnet(NodesShutdownAction.INSTANCE)
  implicit val nodesStatsAction = magnet(NodesStatsAction.INSTANCE)
  implicit val openIndexAction = magnet(OpenIndexAction.INSTANCE)
  implicit val optimizeAction = magnet(OptimizeAction.INSTANCE)
  implicit val pendingClusterTasksAction = magnet(PendingClusterTasksAction.INSTANCE)
  implicit val percolateAction = magnet(PercolateAction.INSTANCE)
  implicit val putIndexTemplateAction = magnet(PutIndexTemplateAction.INSTANCE)
  implicit val putMappingAction = magnet(PutMappingAction.INSTANCE)
  implicit val putRepositoryAction = magnet(PutRepositoryAction.INSTANCE)
  implicit val putWarmerAction = magnet(PutWarmerAction.INSTANCE)
  implicit val refreshAction = magnet(RefreshAction.INSTANCE)
  implicit val restoreSnapshotAction = magnet(RestoreSnapshotAction.INSTANCE)
  implicit val searchAction = magnet(SearchAction.INSTANCE)
  implicit val searchScrollAction = magnet(SearchScrollAction.INSTANCE)
  implicit val suggestAction = magnet(SuggestAction.INSTANCE)
  implicit val termVectorAction = magnet(TermVectorAction.INSTANCE)
  implicit val typesExistsAction = magnet(TypesExistsAction.INSTANCE)
  implicit val updateAction = magnet(UpdateAction.INSTANCE)
  implicit val updateSettingsAction = magnet(UpdateSettingsAction.INSTANCE)
  implicit val validateQueryAction = magnet(ValidateQueryAction.INSTANCE)

  private def magnet[Request <: ActionRequest[Request], Response <: ActionResponse, RequestBuilder <: ActionRequestBuilder[Request, Response, RequestBuilder]](action: Action[Request, Response, RequestBuilder]) =
    new ActionMagnet[Request, Response] {
      def execute(javaClient: Client, request: Request) = {
        val promise = Promise[Response]()
        javaClient.execute(action, request, actionListener(promise))
        promise.future
      }
    }

  private def magnet[Request <: ActionRequest[Request], Response <: ActionResponse, RequestBuilder <: ActionRequestBuilder[Request, Response, RequestBuilder]](action: ClusterAction[Request, Response, RequestBuilder]) =
    new ActionMagnet[Request, Response] {
      def execute(javaClient: Client, request: Request) = {
        val promise = Promise[Response]()
        javaClient.admin.cluster.execute(action, request, actionListener(promise))
        promise.future
      }
    }

  private def magnet[Request <: ActionRequest[Request], Response <: ActionResponse, RequestBuilder <: ActionRequestBuilder[Request, Response, RequestBuilder]](action: IndicesAction[Request, Response, RequestBuilder]) =
    new ActionMagnet[Request, Response] {
      def execute(javaClient: Client, request: Request) = {
        val promise = Promise[Response]()
        javaClient.admin.indices.execute(action, request, actionListener(promise))
        promise.future
      }
    }

  private def actionListener[A](promise: Promise[A]) = new ActionListener[A] {
    def onResponse(response: A) {
      promise.success(response)
    }
    def onFailure(e: Throwable) {
      promise.failure(e)
    }
  }
}
