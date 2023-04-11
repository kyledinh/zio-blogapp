package blogapp.views

import com.raquo.laminar.api.L.{*, given}
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}

import blogapp.Component

final case class PortfolioSection() extends Component {

  val body: Div =
    div(
      cls("section"),
      div(
        cls("container"),
        div(
          cls("row justify-content-center mb-5"),
          div(
            cls("col-lg-6 text-center aos-init aos-animate"),
            attrDataAos("fade-up"),
            attrDataAosDelay("100"),
            span(cls("subheading mb-2 d-inline-block"), "Awesome Portfolio"),
            h2(cls("mb-4"), "Our Portfolio")
          )
        ),
        div(
          cls("row"),
          div(
            cls("col-md-6 col-lg-4 aos-init aos-animate"),
            attrDataAos("fade-up"),
            attrDataAosDelay("200"),
            a(
              cls("media-1 glightbox"),
              href("medio/images/img_v_1-min.jpg"),
              img(cls("img-fluid"), src("medio/images/img_v_1-min.jpg")),
              div(cls("media-1-content"), h2("Bonzai Tree"), span(cls("category"), "Web Application"))
            )
          ),
          div(
            cls("col-md-6 col-lg-4 aos-init aos-animate"),
            attrDataAos("fade-up"),
            attrDataAosDelay("300"),
            a(
              cls("media-1 glightbox"),
              href("medio/images/img_v_2-min.jpg"),
              img(cls("img-fluid"), src("medio/images/img_v_2-min.jpg")),
              div(cls("media-1-content"), h2("Simple Woman"), span(cls("category"), "Branding"))
            )
          ),
          div(
            cls("col-md-6 col-lg-4 aos-init aos-animate"),
            attrDataAos("fade-up"),
            attrDataAosDelay("400"),
            a(
              cls("media-1 glightbox"),
              href("medio/images/img_v_3-min.jpg"),
              img(cls("img-fluid"), src("medio/images/img_v_3-min.jpg")),
              div(cls("media-1-content"), h2("Fruits"), span(cls("category"), "Website"))
            )
          ),
          div(
            cls("col-md-6 col-lg-4 aos-init aos-animate"),
            attrDataAos("fade-up"),
            attrDataAosDelay("100"),
            a(
              cls("media-1 glightbox"),
              href("medio/images/img_v_4-min.jpg"),
              img(cls("img-fluid"), src("medio/images/img_v_4-min.jpg")),
              div(cls("media-1-content"), h2("Design Material"), span(cls("category"), "Web Application"))
            )
          ),
          div(
            cls("col-md-6 col-lg-4 aos-init aos-animate"),
            attrDataAos("fade-up"),
            attrDataAosDelay("200"),
            a(
              cls("media-1 glightbox"),
              href("medio/images/img_v_5-min.jpg"),
              img(cls("img-fluid"), src("medio/images/img_v_5-min.jpg")),
              div(cls("media-1-content"), h2("Handy Food"), span(cls("category"), "Branding"))
            )
          ),
          div(
            cls("col-md-6 col-lg-4 aos-init aos-animate"),
            attrDataAos("fade-up"),
            attrDataAosDelay("300"),
            a(
              cls("media-1 glightbox"),
              href("medio/images/img_v_6-min.jpg"),
              img(cls("img-fluid"), src("medio/images/img_v_6-min.jpg")),
              div(cls("media-1-content"), h2("Cat With Cup"), span(cls("category"), "Website"))
            )
          )
        )
      )
    )

}
