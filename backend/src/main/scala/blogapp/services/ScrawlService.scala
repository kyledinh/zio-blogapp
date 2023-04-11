package blogapp.services

import blogapp.models.{PubStatus, Scrawl, Uuid}
import zio.*

import java.time.LocalDate

trait ScrawlService {

  def create(
      title: String,
      body: String,
      createDate: LocalDate,
      status: PubStatus,
      personId: Uuid
  ): Task[Scrawl]

  def delete(id: Uuid): Task[Unit]

  def get(id: Uuid): Task[Option[Scrawl]]

  def getForPerson(personId: Uuid): Task[List[Scrawl]]

  def getAll: Task[List[Scrawl]]

  def update(
      id: Uuid,
      title: Option[String],
      body: Option[String],
      status: Option[PubStatus]
  ): Task[Unit]

}
