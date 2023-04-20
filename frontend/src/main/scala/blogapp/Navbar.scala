package blogapp

import com.raquo.laminar.api.L.{*, given}
import blogapp.views.components.Bootstrap.*
import blogapp.Page.*
import com.raquo.laminar.nodes
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr

final case class Navbar() extends Component {

  type Nav = nodes.ReactiveHtmlElement[dom.html.Div]

  private def navLink(text: String, page: Page): LI = {
    val $isActive = false
    li(
      cls("nav-item"),
      a(cls("nav-link"), text, cursor.pointer),
      onClick --> { _ =>
        Router.router.pushState(page)
      }
    )
  }

  private def navbarTogglerButton(targetId: String): Button =
    button(
      cls("navbar-toggler collapsed"),
      typ("button"),
      attrDataBsToggle("collapse"),
      attrDataBsTarget(targetId),
      attrAriaControls("navbarResponsive"),
      attrAriaExpanded("false"),
      attrAriaLabel("Toggle navigation"),
      span(cls("navbar-toggler-icon"))
    )

  def body: Nav =
    div(
      cls("navbar navbar-expand-lg fixed-top navbar-dark bg-dark"),
      div(
        cls("container"),
        a(cls("navbar-brand"), href("/"), "ZIO BlogApp"),
        navbarTogglerButton("#navbarResponsive"),
        div(
          cls("collapse navbar-collapse"),
          idAttr("navbarResponsive"),
          ul(
            cls("navbar-nav"),
            navLink("Home", HomePage),
            navLink("Board", BoardPage),
            navLink("People", PeoplePage),
            navLink("Medio", MedioPage)
          ),
          ul(
            cls("navbar-nav ms-md-auto"),
            li(
              cls("nav-item"),
              a(cls("nav-link"), href("https://github.com/kyledinh/zio-blogapp"), i(cls("bi bi-github"), "GitHub"))
            ),
            li(cls("nav-item"), a(cls("nav-link"), href("https://kyledinh.com"), i(cls("bi bi-twitter"), "Website")))
          )
        )
      )
    )
}
