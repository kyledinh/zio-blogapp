package blogapp.server

import blogapp.models.api.{CreatePerson, UpdatePerson}
import blogapp.server.ServerUtils.{parseBody, parseUuid}
import blogapp.services.PersonService
import zhttp.http.*
import zio.*
import zio.json.*

final case class PersonRoutes(service: PersonService) {

  val routes: Http[Any, Throwable, Request, Response] = Http.collectZIO[Request] {

    case Method.GET -> !! / "persons" =>
      service.getAll.map(users => Response.json(users.toJson))

    case Method.GET -> !! / "person" / id =>
      for {
        id     <- parseUuid(id)
        person <- service.get(id)
      } yield Response.json(person.toJson)

    case req @ Method.POST -> !! / "person" =>
      for {
        personBody <- parseBody[CreatePerson](req)
        person <-
          service.create(
            personBody.firstName,
            personBody.lastName,
            personBody.address,
            personBody.phone,
            personBody.email
          )
      } yield Response.json(person.toJson)

    case req @ Method.PATCH -> !! / "person" / id =>
      for {
        personId     <- parseUuid(id)
        personUpdate <- parseBody[UpdatePerson](req)
        _ <- service.update(
               personId,
               personUpdate.firstName,
               personUpdate.lastName,
               personUpdate.address,
               personUpdate.phone,
               personUpdate.email
             )
      } yield Response.ok

    case Method.DELETE -> !! / "person" / id =>
      for {
        id <- parseUuid(id)
        _  <- service.delete(id)
      } yield Response.ok

  }

}

object PersonRoutes {
  val layer: URLayer[PersonService, PersonRoutes] = ZLayer.fromFunction(PersonRoutes.apply _)
}
