package blogapp.views

import com.raquo.laminar.api.L.{*, given}
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}

import blogapp.Component

final case class NewsletterSection() extends Component {

  val body: Div =
    div(cls("section section-contact m-md-5 px-md-5"),
      div(cls("container"),
        h2("Subscribe to Newsleter"),
        p("Lorem ipsom dolor sit amet, consectetur adipiscing elit"),
        form(cls("form-contact"),
          action(""),
          div(cls("row"),
            div(cls("col-sm-4 col-md-4 col-lg-4"),
              div(cls("form-group"),
                input(cls("form-control"), typ("text"), placeholder("Your name"))
              )
            ),
            div(cls("col-sm-4 col-md-4 col-lg-4"),
              div(cls("form-group"),
                input(cls("form-control"), typ("email"), placeholder("Your email"))
              )
            ),
            div(cls("col-sm-4 col-md-4 col-lg-4"),
              div(cls("form-group"),
                input(cls("btn btn-black btn-block"), typ("submit"), placeholder("Subscribe"))
              )
            ),
          )
        )
      )
    )

}
