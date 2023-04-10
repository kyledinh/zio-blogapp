package blogapp.services

import blogapp.QuillContext
import blogapp.models.{Person, Roles, Uuid}
import blogapp.services.PersonService
import zio.*
import io.getquill.*

import javax.sql.DataSource

final case class PersonServiceLive(dataSource: DataSource) extends PersonService {

  import QuillContext._

  override def create(
      firstName: String,
      lastName: String,
      address: String,
      phone: String,
      email: String
  ): Task[Person] =
    for {
      person <- Person.make(firstName, lastName, address, phone, email)
      _      <- run(query[Person].insertValue(lift(person))).provideEnvironment(ZEnvironment(dataSource))
    } yield person

  override def delete(id: Uuid): Task[Unit] =
    run(query[Person].filter(_.id == lift(id)).delete)
      .provideEnvironment(ZEnvironment(dataSource))
      .unit

  /** `get` uses `filter` to find an Owner in the database whose ID matches the
    * one provided and returns it.
    */
  override def get(id: Uuid): Task[Option[Person]] =
    run(query[Person].filter(_.id == lift(id)))
      .provideEnvironment(ZEnvironment(dataSource))
      .map(_.headOption)

  override def getAll: Task[List[Person]] =
    run(query[Person])
      .provideEnvironment(ZEnvironment(dataSource))

  /** `update` uses `filter` to find an Owner in the database whose ID matches
    * the one provided and updates it with the provided optional values.
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
      firstName: Option[String],
      lastName: Option[String],
      address: Option[String],
      phone: Option[String],
      email: Option[String]
  ): Task[Unit] =
    run(
      dynamicQuery[Person]
        .filter(_.id == lift(id))
        .update(
          setOpt(_.firstName, firstName),
          setOpt(_.lastName, lastName),
          setOpt(_.address, address),
          setOpt(_.phone, phone),
          setOpt(_.email, email)
        )
    )
      .provideEnvironment(ZEnvironment(dataSource))
      .unit

}

object PersonServiceLive {
  val layer: URLayer[DataSource, PersonService] = ZLayer.fromFunction(PersonServiceLive.apply _)
}
