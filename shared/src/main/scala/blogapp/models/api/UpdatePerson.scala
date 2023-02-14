package blogapp.models.api

import blogapp.models.{Roles}

import zio.json.*

final case class UpdatePerson(
    firstName: Option[String],
    lastName: Option[String],
    address: Option[String],
    phone: Option[String],
    email: Option[String]
)

object UpdatePerson {
  implicit val codec: JsonCodec[UpdatePerson] =
    DeriveJsonCodec.gen[UpdatePerson]
}
