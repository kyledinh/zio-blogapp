package blogapp 

import com.raquo.laminar.api.L.{Owner => _, _}
import com.raquo.laminar.api.L.{*, given}
import blogapp.views._

object MainPage {
  def body: Div =
    div(
      NavBar(),
      child <-- Router.router.$currentPage.map {
        // case Page.OwnersPage         => OwnerIndexView()
        case Page.PersonPage(id)      => PersonView(id)
        case Page.HomePage            => HomeView()
        case Page.BoardPage           => BoardView()
        case Page.MedioPage           => MedioView()
        case Page.PeoplePage          => PeopleView()
      }
    )
}
