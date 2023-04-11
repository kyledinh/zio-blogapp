package blogapp.services

import blogapp.QuillContext
import blogapp.models.{PubStatus, Scrawl, Uuid}
import zio.*
import zio.metrics.*
import io.getquill.*

import javax.sql.DataSource

import blogapp.services.ScrawlService

final case class ScrawlServiceLive(dataSource: DataSource) extends ScrawlService {

  // QuillContext needs to be imported here to expose the methods in the QuillContext object.
  import QuillContext._

  /** `encodeStatus` and `decodeStatus` are helper functions used to convert
    * Species to strings and strings to PubStatus respectively.
    */
  implicit val encodeStatus: MappedEncoding[PubStatus, String] = MappedEncoding[PubStatus, String](_.toString)
  implicit val decodeStatus: MappedEncoding[String, PubStatus] = MappedEncoding[String, PubStatus](PubStatus.fromString)

  override def create(
      title: String,
      body: String,
      createDate: java.time.LocalDate,
      status: PubStatus,
      personId: Uuid
  ): Task[Scrawl] =
    for {
      scrawl <- Scrawl.make(title, body, createDate, status, personId)
      _      <- run(query[Scrawl].insertValue(lift(scrawl))).provideEnvironment(ZEnvironment(dataSource))
      _      <- Metric.counter("scrawl.created").increment
    } yield scrawl

  override def delete(id: Uuid): Task[Unit] =
    run(query[Scrawl].filter(_.id == lift(id)).delete)
      .provideEnvironment(ZEnvironment(dataSource))
      .unit

  override def get(id: Uuid): Task[Option[Scrawl]] =
    run(query[Scrawl].filter(_.id == lift(id)))
      .provideEnvironment(ZEnvironment(dataSource))
      .map(_.headOption)

  override def getForPerson(personId: Uuid): Task[List[Scrawl]] =
    run(query[Scrawl].filter(_.personId == lift(personId)).sortBy(_.createDate))
      .provideEnvironment(ZEnvironment(dataSource))

  override def getAll: Task[List[Scrawl]] =
    run(query[Scrawl].sortBy(_.createDate))
      .provideEnvironment(ZEnvironment(dataSource))

  /** `update` uses `filter` to find a Pet in the database whose ID matches the
    * one provided and updates it with the provided optional values.
    *
    * Because a user may not provide all optional values, `setOpt` is used to
    * preserve the existing value in the case one is not provided to replace it.
    * Unit is returned to indicate side-effecting code. Note that this
    * `dynamicQuery` is not generated at compile time.
    *
    * For more information on dynamic queries, see:
    * https://getquill.io/#writing-queries-dynamic-queries
    */
  override def update(
      id: Uuid,
      title: Option[String],
      body: Option[String],
      status: Option[PubStatus]
  ): Task[Unit] =
    run(
      dynamicQuery[Scrawl]
        .filter(_.id == lift(id))
        .update(
          setOpt(_.title, title),
          setOpt(_.body, body),
          setOpt(_.status, status)
        )
    )
      .provideEnvironment(ZEnvironment(dataSource))
      .unit

}

object ScrawlServiceLive {
  val layer: URLayer[DataSource, ScrawlService] = ZLayer.fromFunction(ScrawlServiceLive.apply _)
}
