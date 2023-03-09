package blogapp.views

import com.raquo.laminar.api.L.{*, given}

import blogapp.Component

final case class PeopleView() extends Component {
  val body: Div =
    div(cls("section bg-light"),
      div(cls("container"),
        h2("People View"),
        p("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, "),
        p("Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore"),
        p("Excepteur sint occaecat cupidatat non proident"),
      )
    )

  private def bodyLink(name: String, url: String) =
    a(
      cls("text-orange-700 hover:text-orange-600 text-l cursor-pointer"),
      target("_blank"),
      name,
      href(url)
    )
}
