package blogapp

import blogapp.models.*
import com.raquo.laminar.api.L.{Owner => _, _}
import org.scalajs.dom.document
// import blogapp.models.api.{CreateOwner, CreatePet, CreateVisit, UpdateOwner, UpdatePet, UpdateVisit}
import sttp.capabilities
import sttp.client3.*
import zio.json.*
import zio.json.internal.RetractReader

import scala.concurrent.Future

object Requests {
  private val backend: SttpBackend[Future, capabilities.WebSockets] = FetchBackend()

  private val baseUrl =
    if (document.location.host.contains("kyledinh.com"))
      uri"https://blogapi.kyledinh.com"
    else
      uri"http://localhost:4000"

  def getRequest[A: JsonCodec](path: Any*): EventStream[A] = {
    val request = quickRequest.get(uri"$baseUrl/$path")
    EventStream.fromFuture(backend.send(request)).map { response =>
      response.body.fromJson[A] match {
        case Right(b) => b
        case Left(e)  => throw new Error(s"Error parsing JSON: $e")
      }
    }
  }

  def deleteRequest(path: Any*): EventStream[Unit] = {
    val request = quickRequest.delete(uri"$baseUrl/$path")
    EventStream.fromFuture(backend.send(request)).map(_ => ())
  }

  def postRequest[In: JsonEncoder, Out: JsonDecoder](body: In)(path: Any*): EventStream[Out] = {
    val request = quickRequest.post(uri"$baseUrl/$path").body(body.toJson)
    EventStream.fromFuture(backend.send(request)).map { response =>
      response.body.fromJson[Out] match {
        case Right(b) => b
        case Left(e)  => throw new Error(s"Error parsing JSON: $e")
      }
    }
  }

  def patchRequest[In: JsonEncoder, Out: JsonDecoder](body: In)(path: Any*): EventStream[Out] = {
    val request = quickRequest.patch(uri"$baseUrl/$path").body(body.toJson)
    EventStream.fromFuture(backend.send(request)).map { response =>
      response.body.fromJson[Out] match {
        case Right(b) => b
        case Left(e)  => throw new Error(s"Error parsing JSON: $e")
      }
    }
  }

  def getAllPersons(): EventStream[List[Person]] =
    getRequest[List[Person]]("persons")

  def getAllScrawls(): EventStream[List[Scrawl]] =
    getRequest[List[Scrawl]]("scrawls")

  def getPerson(uuid: Uuid): EventStream[Person] =
    getRequest[Person]("person", uuid.id)

  def getScrawlsByPerson(uuid: Uuid): EventStream[List[Scrawl]] =
    getRequest[List[Scrawl]]("scrawls", "person", uuid.id)

  // def addScrawl(createScrawl: CreateScrawl): EventStream[Scrawl] =
  //   postRequest[CreateScawl, Scrawl](createScrawl)("scrawls")


  implicit lazy val unitDecoder: JsonDecoder[Unit] =
    new JsonDecoder[Unit] {
      override def unsafeDecode(trace: List[JsonError], in: RetractReader): Unit = ()
    }
}
