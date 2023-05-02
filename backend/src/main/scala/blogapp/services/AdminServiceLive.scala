package blogapp.services

import blogapp.AppConfig
import blogapp.models.{Person, Roles, Uuid}
import blogapp.services.{AdminService, AppInfo}
import zio.*
import io.getquill.*

import javax.sql.DataSource

final case class AdminServiceLive(dataSource: DataSource) extends AdminService {

  import AppConfig.*

  override def infoAll: Task[AppInfo] =
    ZIO.config[AppConfig](AppConfig.config).map { c =>
      AppInfo(
        host = c.host,
        backendPort = c.port,
        logLevel = c.logLevel
      )
    }

}

object AdminServiceLive {
  val layer: URLayer[DataSource, AdminService] = ZLayer.fromFunction(AdminServiceLive.apply _)
}
