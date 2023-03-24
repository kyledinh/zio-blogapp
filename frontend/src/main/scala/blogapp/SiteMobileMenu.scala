package blogapp 

import com.raquo.laminar.api.L.{*, given}
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay, attrDataToggle, attrDataTarget}

final case class SiteMobileMenu() extends Component {

  def body: Div = {
    div(cls("site-mobile-menu site-navbar-target"),
      div(cls("site-mobile-menu-header"),
        div(cls("site-mobile-menu-close"),
          span(cls("icofont-close js-menu-toggle"))
        )
      ),
      div(cls("site-mobile-menu-body"))
    )
  }
}