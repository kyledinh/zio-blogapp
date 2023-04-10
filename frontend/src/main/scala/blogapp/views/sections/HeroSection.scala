package blogapp.views

import com.raquo.laminar.api.L.{*, given}
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}

import blogapp.Component

final case class HeroSection() extends Component {

  val body: Div =
    div(
      cls("hero v-h-full overlay"),
      div(
        cls("img-bg rellax"),
        img(cls("img-fluid"), src("medio/images/hero_1.jpg"), alt("Image")),
        div(
          cls("container"),
          div(
            cls("row align-items-center v-h-full"),
            div(
              cls("col-lg-10 col-xxl-6"),
              h1(
                cls("heading-2 text-white mb-5 aos-init aos-animate"),
                attrDataAos("fade-up"),
                "We are a creative agency by Untree.co"
              ),
              p(
                cls("d-flex aos-init aos-animate"),
                attrDataAos("fade-up"),
                attrDataAosDelay("200"),
                a(cls("btn btn-primary-outline white me-4"), href("#"), "See Portfolio"),
                a(
                  cls("glightbox d-inline-flex align-items-center playvid"),
                  href("https://https://www.youtube.com/watch?v="),
                  span(cls("icon-play me-2")),
                  span("Watch our Video")
                )
              )
            )
          )
        )
      )
    )

}
