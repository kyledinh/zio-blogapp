package blogapp

import blogapp.models.*
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

final case class Footer() extends Component {

  def body =
    div(
      idAttr("footer"),
      div(
        cls("d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top"),
        p(cls("col-md-4 mb-0 text-muted"), idAttr("blogappSemver")),
        a(
          href("https://kyledinh.com"),
          target("_blank"),
          img(src("https://kyledinh.com/agency/img/logos/walvis.svg"), height("50px"))
        ),
        ul(
          cls("nav col-md-4 justify-content-end"),
          extLink("ZIO", "https://zio.dev/"),
          extLink("GitHub", "https://github.com/kyledinh/zio-blogapp"),
          extLink("Laminar", "https://laminar.dev/documentation"),
          extLink("Bootstrap", "https://bootswatch.com/simplex/")
        )
      )
    )

  private def extLink(name: String, url: String) =
    li(
      cls("nav-item"),
      a(
        cls("nav-link px-2 text-muted"),
        target("_blank"),
        name,
        href(url)
      )
    )
}

/*
// https://getbootstrap.com/docs/5.1/examples/footers/
<footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    <p class="col-md-4 mb-0 text-muted">© 2021 Company, Inc</p>

    <a href="/" class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
      <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
    </a>

    <ul class="nav col-md-4 justify-content-end">
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Home</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Features</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Pricing</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">FAQs</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">About</a></li>
    </ul>
  </footer>
*/

