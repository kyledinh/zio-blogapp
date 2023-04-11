package blogapp.server

import blogapp.models.Uuid
import zhttp.http.Request
import zio.json.*
import zio.{Console, IO, ZIO}

object ServerUtils {

  def parseBody[A: JsonDecoder](request: Request): IO[AppError, A] =
    for {
      body   <- request.body.asString.orElseFail(AppError.MissingBodyError)
      parsed <- ZIO.from(body.fromJson[A]).mapError(msg => new AppError.JsonDecodingError(msg))
    } yield parsed

  def parseUuid(id: String): IO[AppError.InvalidIdError, Uuid] =
    (for {
      uid <- Uuid.fromString(id)
    } yield uid).orElseFail(AppError.InvalidIdError("Invalid uuid"))

}
