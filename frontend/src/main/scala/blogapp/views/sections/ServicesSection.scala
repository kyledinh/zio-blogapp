package blogapp.views

import com.raquo.laminar.api.L.{*, given}
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}

import blogapp.Component

final case class ServicesSection() extends Component {

  val body: Div =
    div(
      cls("section bg-light"),
      div(
        cls("container"),
        div(
          cls("row"),
          div(
            cls("col-lg-4 mb-lg-0 aos-init aos-animate"),
            attrDataAos("fade-up"),
            attrDataAosDelay("0"),
            div(
              cls("service grayscale"),
              div(cls("service-img"), a(href("#"), img(cls("img-fluid"), src("medio/images/img_1.jpg")))),
              div(
                cls("service-inner"),
                h3("Collaborate"),
                p(
                  "Far far away, behind the word mountains, far from the countries Consonansita, there live the blind texts."
                ),
                p(cls("mb-0"), a(cls("more"), href("#"), "Learn more"))
              )
            )
          ),
          div(
            cls("col-lg-4 mb-lg-0 aos-init aos-animate"),
            attrDataAos("fade-up"),
            attrDataAosDelay("100"),
            div(
              cls("service grayscale"),
              div(cls("service-img"), a(href("#"), img(cls("img-fluid"), src("medio/images/img_2.jpg")))),
              div(
                cls("service-inner"),
                h3("Connect"),
                p(
                  "Far far away, behind the word mountains, far from the countries Consonansita, there live the blind texts."
                ),
                p(cls("mb-0"), a(cls("more"), href("#"), "Learn more"))
              )
            )
          ),
          div(
            cls("col-lg-4 mb-lg-0 aos-init aos-animate"),
            attrDataAos("fade-up"),
            attrDataAosDelay("200"),
            div(
              cls("service grayscale"),
              div(cls("service-img"), a(href("#"), img(cls("img-fluid"), src("medio/images/img_3.jpg")))),
              div(
                cls("service-inner"),
                h3("Grow"),
                p(
                  "Far far away, behind the word mountains, far from the countries Consonansita, there live the blind texts."
                ),
                p(cls("mb-0"), a(cls("more"), href("#"), "Learn more"))
              )
            )
          )
        )
      )
    )

}
