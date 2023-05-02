package blogapp.services

import blogapp.models.{Person, Roles, Uuid}
import zio.json.*
import zio.*

case class AppInfo(
    host: String,
    backendPort: Int,
    runMode: String,
    logLevel: String
)

object AppInfo {
  implicit val codec: JsonCodec[AppInfo] =
    DeriveJsonCodec.gen[AppInfo]
}

trait AdminService {
  def infoAll: Task[AppInfo]
}
