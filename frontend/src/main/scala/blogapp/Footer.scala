package blogapp 

import com.raquo.laminar.api.L._

final case class Footer() extends Component {
  def body =
    div(
      cls(
        "sticky bottom-0 p-4 px-8 w-full bg-gray-200 whitespace-nowrap"
      ),
      div(
        cls(
          "flex justify-between"
        ),
        div(
          footerLink("CSS template designed love by Untree.co ", "https://untree.co"),
          cls("text-gray-600 text-sm italic"),
          "⚠️ Used for demo purposes. Scroll down to see the Medio template."
        ),
        div(
          cls("flex"),
          footerLink("ZIO Docs", "https://zio.dev/"),
          footerLink("GitHub Repo", "https://github.com/kyledinh/zio-blogapp")
        )
      )
    )

  private def footerLink(name: String, url: String) =
    a(
      cls("text-gray-600 hover:text-gray-500 font-medium cursor-pointer ml-6"),
      target("_blank"),
      name,
      href(url)
    )
}
