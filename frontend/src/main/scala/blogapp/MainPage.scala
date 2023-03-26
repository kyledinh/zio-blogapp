package blogapp 

import com.raquo.laminar.api.L.{Owner => _, _}
import com.raquo.laminar.api.L.{*, given}
import blogapp.views._

object MainPage {
  def body: Div =
    div(
      SiteMobileMenu(),
      SiteNav(),
      div(cls("section bg-dark"), padding("50px 0px")),
      child <-- Router.router.$currentPage.map {
        case Page.HomePage            => HomeView()
        case Page.BoardPage           => BoardView()
        case Page.MedioPage           => MedioView()
        case Page.PeoplePage          => PeopleView()
        case Page.PersonPage(id)      => PersonView(id)
      }
    )
}
