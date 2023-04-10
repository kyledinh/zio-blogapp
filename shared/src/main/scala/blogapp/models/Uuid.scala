package blogapp.models

import zio._
import zio.json._
import java.util.UUID

final case class Uuid(id: UUID) extends AnyVal

object Uuid {

  def random: UIO[Uuid] = Random.nextUUID.map(Uuid(_))

  def fromString(id: String): Task[Uuid] =
    ZIO.attempt {
      Uuid(UUID.fromString(id))
    }

  // def asString: UIO[String] = id.toString

  implicit val codec: JsonCodec[Uuid] =
    JsonCodec[UUID].transform(Uuid(_), _.id)
}
