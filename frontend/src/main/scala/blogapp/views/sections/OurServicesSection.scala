package blogapp.views

import com.raquo.laminar.api.L.{*, given}
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}

import blogapp.Component

final case class OurServicesSection() extends Component {

    val body: Div =
        div(cls("sectio bg-light"),  
            div(cls("container"),
                div(cls("row mb-5"),
                    div(cls("col-12 text-center aos-init aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("100"),
                        span(cls("subheading d-line-block mb-2"),
                        "Our Services",
                        h2("What we can do for you?")
                        )
                    )
                ),
                div(cls("row align-items-stretch"),
                    div(cls("col-md-6 col-lg-4 mb-lg-5 aos-init aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("1"),
                        div(cls("unit-4 d-flex"),
                            div(cls("unit-4-icon me-4"),
                                span(cls("feather-briefcase"))
                            ),
                            div(
                                h3("Web Design"),
                                p("Lorem Ipsum is simply dummy text"),
                                p(a(cls("more"), href("#"), "Learn More"))
                            ),
                        )
                    ),
                    div(cls("col-md-6 col-lg-4 mb-lg-5 aos-init aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("100"),
                        div(cls("unit-4 d-flex"),
                            div(cls("unit-4-icon me-4"),
                                span(cls("feather-command"))
                            ),
                            div(
                                h3("Programming"),
                                p("Lorem Ipsum is simply dummy text"),
                                p(a(cls("more"), href("#"), "Learn More"))
                            ),
                        )
                    ),
                    div(cls("col-md-6 col-lg-4 mb-lg-5 aos-init aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("200"),
                        div(cls("unit-4 d-flex"),
                            div(cls("unit-4-icon me-4"),
                                span(cls("feather-cpu"))
                            ),
                            div(
                                h3("Cloud Infrastructure"),
                                p("Lorem Ipsum is simply dummy text"),
                                p(a(cls("more"), href("#"), "Learn More"))
                            ),
                        )
                    ),
                    div(cls("col-md-6 col-lg-4 mb-lg-5 aos-init aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("300"),
                        div(cls("unit-4 d-flex"),
                            div(cls("unit-4-icon me-4"),
                                span(cls("feather-home"))
                            ),
                            div(
                                h3("Settings"),
                                p("Lorem Ipsum is simply dummy text"),
                                p(a(cls("more"), href("#"), "Learn More"))
                            ),
                        )
                    ),
                    div(cls("col-md-6 col-lg-4 mb-lg-5 aos-init aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("400"),
                        div(cls("unit-4 d-flex"),
                            div(cls("unit-4-icon me-4"),
                                span(cls("feather-feather"))
                            ),
                            div(
                                h3("Writing"),
                                p("Lorem Ipsum is simply dummy text"),
                                p(a(cls("more"), href("#"), "Learn More"))
                            ),
                        )
                    ),
                    div(cls("col-md-6 col-lg-4 mb-lg-5 aos-init aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("500"),
                        div(cls("unit-4 d-flex"),
                            div(cls("unit-4-icon me-4"),
                                span(cls("feather-file-text"))
                            ),
                            div(
                                h3("Mobile Apps"),
                                p("Lorem Ipsum is simply dummy text"),
                                p(a(cls("more"), href("#"), "Learn More"))
                            ),
                        )
                    )                                        

                )
            )
        )


}