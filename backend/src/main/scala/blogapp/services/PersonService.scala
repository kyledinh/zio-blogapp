package blogapp.services

import blogapp.models.{Person, Roles, Uuid}
import zio.*

trait PersonService {
 
  def create(
    firstName: String, 
    lastName: String, 
    address: String, 
    phone: String, 
    email: String 
  ): Task[Person]
 
  def delete(id: Uuid): Task[Unit]
 
  def get(id: Uuid): Task[Option[Person]]
 
  def getAll: Task[List[Person]]
 
  def update(
      id: Uuid,
      firstName: Option[String],
      lastName: Option[String],
      address: Option[String],
      phone: Option[String],
      email: Option[String]
  ): Task[Unit]

}
