package blogapp.models.api

import blogapp.models.{PubStatus, Uuid}
import zio.json.*

final case class UpdateScrawl(
    id: Uuid,
    title: Option[String],
    body: Option[String],
    status: Option[PubStatus],
    personId: Uuid
)

object UpdateScrawl {
  implicit val codec: JsonCodec[UpdateScrawl] = DeriveJsonCodec.gen[UpdateScrawl]
}
