package blogapp.server

import blogapp.models.api.{CreateScrawl, UpdateScrawl}
import blogapp.server.ServerUtils.*
import blogapp.services.ScrawlService
import zhttp.http.*
import zio.*
import zio.json.*

final case class ScrawlRoutes(service: ScrawlService) {

  val routes: Http[Any, Throwable, Request, Response] = Http.collectZIO[Request] {

    // Gets all of the Scrawls in the database and returns them as JSON.
    case Method.GET -> !! / "scrawls" =>
      service.getAll.map(scrawls => Response.json(scrawls.toJson))

    // Gets a single Scrawl found by their parsed ID and returns it as JSON.
    case Method.GET -> !! / "scrawl" / id =>
      for {
        id     <- parseUuid(id)
        scrawl <- service.get(id)
      } yield Response.json(scrawl.toJson)

    // Gets all of the Scrawls in the database associated with a particular user and returns them as JSON.
    case Method.GET -> !! / "scrawls" / "person" / id =>
      for {
        id      <- parseUuid(id)
        scrawls <- service.getForPerson(id)
      } yield Response.json(scrawls.toJson)

    case req @ Method.POST -> !! / "scrawl" =>
      for {
        b      <- parseBody[CreateScrawl](req)
        scrawl <- service.create(b.title, b.body, b.createDate, b.status, b.personId)
      } yield Response.json(scrawl.toJson)

    case req @ Method.PATCH -> !! / "scrawl" / id =>
      for {
        id <- parseUuid(id)
        b  <- parseBody[UpdateScrawl](req)
        _  <- service.update(id, b.title, b.body, b.status)
      } yield Response.ok

    case Method.DELETE -> !! / "scrawl" / id =>
      for {
        id <- parseUuid(id)
        _  <- service.delete(id)
      } yield Response.ok

  }

}

object ScrawlRoutes {
  val layer: URLayer[ScrawlService, ScrawlRoutes] = ZLayer.fromFunction(ScrawlRoutes.apply _)
}
