package blogapp.views

import com.raquo.laminar.api.L.{*, given}

import blogapp.Component

final case class HomeView() extends Component {
  val body: Div =
    div(
      cls("bs-docs-section clearfix"),
      div(
        cls := "row",
        div(
          cls := "container",
          h2("Welcome to Blog App"),
          div(
            "A fullstack, idiomatic web app that serves as an example for best utilizing ZIO and the libraries within its ecosystem."
          ),
          div(
            "Navigate to the Owners tab to preview a list of preloaded pet owners. Select an owner to do things like view their personal information, add pets, update visits for a pet, and create owners."
          ),
          div("This version of the website uses Scala 3!")
        )
      )
    )

  private def bodyLink(name: String, url: String) =
    a(cls := "text-orange-700 hover:text-orange-600 text-l cursor-pointer", target := "_blank", name, href := url)
}
