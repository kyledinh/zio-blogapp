package blogapp.views

import com.raquo.laminar.api.L.{*, given}
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}

import blogapp.Component

final case class GetStartedSection() extends Component {

    val body: Div =
        div(cls("section"),
            div(cls("container"),
                div(cls("row"),
                    div(cls("col-lg-4 pe-lg-5 aos-init aos-animate"), attrDataAos("fade-up"),  
                        span(cls("subheading d-inline-block mb-2"), "We love design"),
                        h2(cls("mb-4"), "Create Outstanding Website"),
                        p(cls("mb-5"), "Far far awy, behind the word mountains, far from the counties Volkalia."),
                        p(cls("btn btn-black"), href("#"), "Get started")
                    ),
                    div(cls("col-lg-8"),
                        div(cls("row"),
                            div(cls("col-6 col-lg-6 mt-5 aos-kinit aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("100"),
                                img(cls("img-fluid rounded"), src("medio/images/img_v_5-min.jpg"))
                            ),
                            div(cls("col-6 col-lg-6 mt-5 aos-kinit aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("200"),
                                img(cls("img-fluid rounded"), src("medio/images/img_v_6-min.jpg"))
                            ),
                        )
                    )
                )
            )
        )

}
