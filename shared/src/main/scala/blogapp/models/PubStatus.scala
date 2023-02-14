package blogapp.models

import zio.json._

sealed trait PubStatus {
  def status: String
}

object PubStatus {

  case object Empty extends PubStatus {
    override def status: String = "Select ..."
  }

  case object Draft extends PubStatus  {
    override def status: String = "Draft"
  }

  case object Active extends PubStatus {
    override def status: String = "Active"
  }

  case object Private extends PubStatus {
    override def status: String = "Private"
  }

  case object Past extends PubStatus {
    override def status: String = "Past"
  }


  /** Converts a string to its Species representation. */
  def fromString(s: String): PubStatus = s match {
    case "Draft"    => Draft
    case "Active"   => Active 
    case "Private"  => Private 
    case "Past"     => Past 
    case _          => Empty
  }

  val all: List[PubStatus] = List(Empty, Draft, Active, Private, Past)

  implicit val codec: JsonCodec[PubStatus] = DeriveJsonCodec.gen[PubStatus]

}
