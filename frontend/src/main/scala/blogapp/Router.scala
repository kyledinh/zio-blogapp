package blogapp

import com.raquo.laminar.api.L
import com.raquo.waypoint.*
import com.raquo.laminar.api.eventPropToProcessor

import blogapp.models.Uuid
import zio.json.*

import java.util.UUID

sealed trait Page

object Page {
  // case object OwnersPage                  extends Page
  final case class PersonPage(id: Uuid) extends Page
  case object HomePage                  extends Page
  case object BoardPage                 extends Page
  case object MedioPage                 extends Page
  case object PeoplePage                extends Page

  implicit val codec: JsonCodec[Page] = DeriveJsonCodec.gen[Page]
}

object Router {
  import Page.*

  val homeRoute: Route[Page.HomePage.type, Unit] =
    Route.static(HomePage, root / endOfSegments)

  val boardRoute: Route[Page.BoardPage.type, Unit] =
    Route.static(BoardPage, root / "board" / endOfSegments)

  val medioRoute: Route[Page.MedioPage.type, Unit] =
    Route.static(MedioPage, root / "medio" / endOfSegments)

  val peopleRoute: Route[Page.PeoplePage.type, Unit] =
    Route.static(PeoplePage, root / "people" / endOfSegments)

  val personRoute: Route[PersonPage, String] = Route(
    encode = (userPage: Page.PersonPage) => userPage.id.id.toString,
    decode = (id: String) => PersonPage(Uuid(UUID.fromString(id))),
    pattern = root / "person" / segment[String] / endOfSegments
  )

  val router = new Router[Page](
    routes = List(homeRoute, boardRoute, medioRoute, peopleRoute, personRoute),
    getPageTitle = _.toString,                                              // mock page title (displayed in the browser tab next to favicon)
    serializePage = page => page.toJson,                                    // serialize page data for storage in History API log
    deserializePage = pageStr => pageStr.fromJson[Page].getOrElse(HomePage) // deserialize the above
  )(
    $popStateEvent = L.windowEvents(_.onPopState), // this is how Waypoint avoids an explicit dependency on Laminar
    owner = L.unsafeWindowOwner                    // this router will live as long as the window
  )
}
