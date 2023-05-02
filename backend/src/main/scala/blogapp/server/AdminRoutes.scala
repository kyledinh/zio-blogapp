package blogapp.server

import blogapp.server.ServerUtils.{parseBody, parseUuid}
import blogapp.services.AdminService
import zhttp.http.*
import zio.*
import zio.json.*

final case class AdminRoutes(service: AdminService) {

  val routes: Http[Any, Throwable, Request, Response] = Http.collectZIO[Request] { case Method.GET -> !! / "info" =>
    for {
      info <- service.infoAll
    } yield Response.json(info.toJson)

  }

}

object AdminRoutes {
  val layer: URLayer[AdminService, AdminRoutes] = ZLayer.fromFunction(AdminRoutes.apply _)
}
