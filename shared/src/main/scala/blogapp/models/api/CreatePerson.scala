package blogapp.models.api

import blogapp.models.Roles
import zio.json.*

final case class CreatePerson(
    firstName: String,
    lastName: String,
    email: String,
    phone: String,
    address: String
)

object CreatePerson {
  implicit val codec: JsonCodec[CreatePerson] =
    DeriveJsonCodec.gen[CreatePerson]
}
