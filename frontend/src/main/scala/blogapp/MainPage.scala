package blogapp

import com.raquo.laminar.api.L.{Owner => _, _}
import com.raquo.laminar.api.L.{*, given}
import blogapp.views._

object MainPage {
  def body: Div =
    div(
      Navbar(),
      div(
        cls("container"),
        div(padding("70px 0px")),
        child <-- Router.router.currentPageSignal.map {
          case Page.HomePage       => HomeView()
          case Page.BoardPage      => BoardView()
          case Page.MedioPage      => MedioView()
          case Page.PeoplePage     => PeopleView()
          case Page.PersonPage(id) => PersonView(id)
        },
        div(padding("20px 0px")),
        Footer()
      )
    )
}
