package blogapp.models

import zio.*
import zio.json.*

import blogapp.models.{Roles, Uuid}

final case class Person(
    id: Uuid,
    firstName: String,
    lastName: String,
    address: String,
    phone: String,
    email: String
) {
  def fullName: String = firstName + " " + lastName
}

object Person {

  def make(
    firstName: String, 
    lastName: String, 
    address: String, 
    phone: String, 
    email: String 
  ): UIO[Person] =
    Uuid.random.map(Person(_, firstName, lastName, address, phone, email ))

  implicit val codec: JsonCodec[Person] =
    DeriveJsonCodec.gen[Person]

}
