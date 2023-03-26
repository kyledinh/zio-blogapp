package blogapp 

import com.raquo.laminar.api.L.{*, given}
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay, attrDataToggle, attrDataTarget}
import blogapp.Page.*

final case class NavBar() extends Component {

  def body: Div =
    div(
      siteMobileMenu(),
      siteNav()
    )

  private def navLink(text: String, page: Page): Div = {
    val $isActive =
      Router.router.$currentPage.map { currentPage =>
        currentPage == page
      }
    div(cls(""),
      cursor.pointer,
      text,
      onClick --> { _ =>
        Router.router.pushState(page)
      },
      cls <-- $isActive.map {
        case true  => "text-orange-700 font-bold hover:text-orange-600"
        case false => "text-gray-500 font-normal hover:text-orange-600"
      }
    )
  }

  private def siteMobileMenu(): Div = {
    div(cls("site-mobile-menu site-navbar-target"),
      div(cls("site-mobile-menu-header"),
        div(cls("site-mobile-menu-close"),
          span(cls("icofont-close js-menu-toggle"))
        )
      ),
      div(cls("site-mobile-menu-body"))
    )
  }

  private def siteNav(): Div = {
    div(
      nav(cls("site-nav"),
        div(cls("container"),
          navLink("ZIO BlogApp", HomePage),
          ul(cls("js-clone-nav d-none d-lg-inline-block text-start site-menu float-end"),
            li(navLink("Home", HomePage)),
            li(navLink("Board", BoardPage)),
            li(navLink("People", PeoplePage)),
            li(navLink("Medio", MedioPage)),
          ),
          a(cls("burger ms-auto float-end site-menu-toggle mt-2 js-menu-toggle d-inline-block d-lg-none"),
            href("#"),
            attrDataToggle("collapse"),
            attrDataTarget("#main-navbar"), 
            span(),
          )
        )
      ),    
      div(cls("section bg-dark"))
    )
  }
}