package blogapp.models

import zio.json._

sealed trait Roles {
  def role: String
}

object Roles {

  case object Empty extends Roles {
    override def role: String = "Select..."
  }

  case object Author extends Roles  {
    override def role: String = "Author"
  }

  case object Reader extends Roles {
    override def role: String = "Reader"
  }

  case object Editor extends Roles {
    override def role: String = "Editor"
  }

  def fromString(s: String): Roles = s match {
    case "Author"  => Author
    case "Reader"  => Reader 
    case "Editor"  => Editor 
    case _         => Empty
  }

  val all: List[Roles] = List(Empty, Author, Reader, Editor)

  implicit val codec: JsonCodec[Roles] = DeriveJsonCodec.gen[Roles]

}
