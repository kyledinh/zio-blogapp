package blogapp 

// import com.raquo.laminar.api.L.{Owner => _, _}
import com.raquo.laminar.api.L.{*, given}
import blogapp.views._

object MainPage {
  def body: Div =
    div(
      NavBar(),
      div(
        div(
          child <-- Router.router.$currentPage.map {
            // case Page.OwnersPage         => OwnerIndexView()
            // case Page.OwnerPage(ownerId) => OwnerViewWrapper(ownerId)
            case Page.HomePage            => HomeView()
            case Page.BoardPage           => BoardView()
          }
        )
      ),
      Footer()
    )
}
