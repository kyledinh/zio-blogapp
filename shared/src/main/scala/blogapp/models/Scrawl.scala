package blogapp.models

import zio.*
import zio.json.*

import blogapp.models.{PubStatus, Uuid}

final case class Scrawl(
    id: Uuid,
    title: String,
    body: String,
    createDate: java.time.LocalDate,
    status: PubStatus,
    personId: Uuid
)

object Scrawl {

  def make(
      title: String,
      body: String,
      createDate: java.time.LocalDate,
      status: PubStatus,
      personId: Uuid
  ): UIO[Scrawl] =
    Uuid.random.map(Scrawl(_, title, body, createDate, status, personId))

  implicit val codec: JsonCodec[Scrawl] = DeriveJsonCodec.gen[Scrawl]

}
