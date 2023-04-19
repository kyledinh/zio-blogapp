package blogapp

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

final case class Footer() extends Component {
  def body =
    div(idAttr("footer"),
      div(cls("section container"),
        div(cls("row"),
          div(cls("col-lg-12"),
            ul(cls("list-unstyled"),
              footerLi("ZIO Docs", "https://zio.dev/"),
              footerLi("GitHub Repo", "https://github.com/kyledinh/zio-blogapp")
            )
          )
        )
      )
    )

  private def footerLi(name: String, url: String) =
    li(
      a(
        cls("text-gray-600 hover:text-gray-500 font-medium cursor-pointer ml-6"),
        target("_blank"),
        name,
        href(url)
      )
    )
}

/*
		<footer id="footer">
			<div class="row">
				<div class="col-lg-12">
					<ul class="list-unstyled">
						<li class="float-end"><a href="#top">Back to top</a></li>
						<li><a href="https://blog.bootswatch.com/">Blog</a></li>
						<li><a href="https://blog.bootswatch.com/rss/">RSS</a></li>
						<li><a href="https://twitter.com/bootswatch">Twitter</a></li>
						<li><a href="https://github.com/thomaspark/bootswatch">GitHub</a></li>
						<li><a href="../help/#api">API</a></li>
						<li><a href="../help/#donate">Donate</a></li>
					</ul>
					<p>Made by <a href="https://kyledinh.com/">Kyle Dinh</a>.</p>
					<p>Code released under the <a href="https://github.com/kyledinh/zio-blogapp/blob/master/LICENSE">MIT License</a>.</p>

				</div>
			</div>
		</footer>
 */
