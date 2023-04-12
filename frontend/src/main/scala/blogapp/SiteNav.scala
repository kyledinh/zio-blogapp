package blogapp

import com.raquo.laminar.api.L.{*, given}
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay, attrDataToggle, attrDataTarget}
import blogapp.Page.*
import com.raquo.laminar.nodes
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

final case class SiteNav() extends Component {

  type Nav = nodes.ReactiveHtmlElement[dom.html.Div]

  private def navLink(text: String, page: Page): LI = {
    val $isActive = false
    // Router.router.$currentPage.map { currentPage =>
    //   currentPage == page
    // }
    li(
      cls(""),
      a(text, textDecoration.none, padding("10px 20px"), display("inline-block"), cursor.pointer),
      onClick --> { _ =>
        Router.router.pushState(page)
      }
    )
  }

  def body: Nav =
    div(
      div(
        cls("site-nav"),
        div(
          cls("container"),
          div(
            cls("site-navigation"),
            a(cls("logo m-0"), href("/"), "ZIO BlogApp"),
            ul(
              cls("js-clone-nav d-none d-lg-inline-block text-start site-menu float-end"),
              listStyle("none"),
              li(navLink("Home", HomePage)),
              li(navLink("Board", BoardPage)),
              li(navLink("People", PeoplePage)),
              li(navLink("Medio", MedioPage))
            ),
            a(
              cls("burger ms-auto float-end site-menu-toggle mt-2 js-menu-toggle d-inline-block d-lg-none"),
              href("#"),
              attrDataToggle("collapse"),
              attrDataTarget("#main-navbar"),
              span()
            )
          )
        )
      )
    )
}
