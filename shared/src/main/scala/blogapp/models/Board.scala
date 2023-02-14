package blogapp.models

import zio.json._

final case class Board(
  id: Uuid, name: String, 
  description: String
)

object Board {

  implicit val codec: JsonCodec[Board] = DeriveJsonCodec.gen[Board]
}
