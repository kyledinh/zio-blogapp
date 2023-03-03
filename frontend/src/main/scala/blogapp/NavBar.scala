package blogapp 

import com.raquo.laminar.api.L.{*, given}
import blogapp.Page.*

final case class NavBar() extends Component {

  def body: Div =
    div(
      div(
        cls("site-mobile-menu site-navbar-target"),
        div(
          cls("site-mobile-menu-header"),
          div(
            cls("site-mobile-menu-close"),
            span(
              cls("icofont-close js-menu-toggle")
            )
          )
        ),
        div(
          cls("site-mobile-menu-body")
        )
      ),
      nav(
        cls("site-nav"),
        div(
          cls("container"),
          div(
            cls("site-navigation"),
            button(
              cls("flex"),
              img(
                src("https://kyledinh.com/agency/img/logos/walvis.svg"),
                height("30px")
              ),
              div(cls("text-gray-700 font-bold text-xl"), "Blog App"),
              onClick --> { _ =>
                Router.router.pushState(Page.HomePage)
              }
            ),
            ul(
              cls("js-clone-nav d-none d-lg-inline-block text-start site-menu float-end"),
              li(
                cls("active"),
                navLink("Home", HomePage)  
              ),
              li(
                navLink("Authors", HomePage)  
              ),
              li(
                navLink("Scrawl Board", BoardPage)  
              ),
            ),
            a(
              href("#"),
              cls("burger ms-auto float-end site-menu-toggle mt-2 js-menu-toggle d-inline-block d-lg-none light"),
              // data-toggle("collapse"),
              // data-target("#main-navbar"),
              span()
            )
          )
        )
      )
    )

  private def navLink(text: String, page: Page): Div = {
    val $isActive =
      Router.router.$currentPage.map { currentPage =>
        currentPage == page
      }
    div(
      cls("ml-6"),
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
}


/* FROM MEDIO
	<div class="site-mobile-menu site-navbar-target">
		<div class="site-mobile-menu-header">
			<div class="site-mobile-menu-close">
				<span class="icofont-close js-menu-toggle"></span>
			</div>
		</div>
		<div class="site-mobile-menu-body"></div>
	</div>

	<nav class="site-nav">
		<div class="container">
			<div class="site-navigation">
				<a href="index.html" class="logo m-0">Medio</a>

				<ul class="js-clone-nav d-none d-lg-inline-block text-start site-menu float-end">
					<li class="active"><a href="index.html">Home</a></li>
					<li class="has-children">
						<a href="#">Dropdown</a>
						<ul class="dropdown">
							<li><a href="#">Menu One</a></li>
							<li class="has-children">
								<a href="#">Menu Two</a>
								<ul class="dropdown">
									<li><a href="#">Sub Menu One</a></li>
									<li><a href="#">Sub Menu Two</a></li>
									<li><a href="#">Sub Menu Three</a></li>
								</ul>
							</li>
							<li><a href="#">Menu Three</a></li>
						</ul>
					</li>
					<li><a href="services.html">Services</a></li>
					<li><a href="portfolio.html">Portfolio</a></li>
					<li><a href="about.html">About</a></li>
					<li><a href="contact.html">Contact</a></li>
				</ul>

				<a href="#" class="burger ms-auto float-end site-menu-toggle mt-2 js-menu-toggle d-inline-block d-lg-none light" data-toggle="collapse" data-target="#main-navbar">
					<span></span>
				</a>
			</div>
		</div>
	</nav>
  */