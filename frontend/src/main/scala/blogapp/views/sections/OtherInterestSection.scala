package blogapp.views

import com.raquo.laminar.api.L.{*, given}
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}

import blogapp.Component

final case class OtherInterestSection() extends Component {

  val body: Div =
      div(cls("section mb-5"),
        div(cls("container"),
          div(cls("row mb-5"),
            div(cls("col-lg-7"), attrDataAos("fade-up"),
              span(cls("subheading mb-2 d-inline-block"),
              h2("You May Also be Interest ")
              ),
            )
          ),
          div(cls("row"),
            div(cls("col-md-6 col-lg-4 mb-4 aos-init aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("100"), 
              a(href("#"), 
                img(src("medio/images/img_v_3-min.jpg"), alt("Image"), cls("img-fluid rounded mb-4")),
                h3(cls("h4 mb-3"),
                  a(href("#"), "Digital Advertising")
                ),
                p("Lorem ipsum dolor sit amet, consectetur adipiscing"),
                p(cls("mb-0"),
                  a(href("#"), cls("more"), "Find out more")
                )
              )
            ),
            div(cls("col-md-6 col-lg-4 mb-4 aos-init aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("200"), 
              a(href("#"), 
                img(src("medio/images/img_v_4-min.jpg"), alt("Image"), cls("img-fluid rounded mb-4")),
                h3(cls("h4 mb-3"),
                  a(href("#"), "Content Creation")
                ),
                p("Lorem ipsum dolor sit amet, consectetur adipiscing"),
                p(cls("mb-0"),
                  a(href("#"), cls("more"), "Find out more")
                )
              )
            ),
            div(cls("col-md-6 col-lg-4 mb-4 aos-init aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("300"), 
              a(href("#"), 
                img(src("medio/images/img_v_6-min.jpg"), alt("Image"), cls("img-fluid rounded mb-4")),
                h3(cls("h4 mb-3"),
                  a(href("#"), "Time and Project Mgt.")
                ),
                p("Lorem ipsum dolor sit amet, consectetur adipiscing"),
                p(cls("mb-0"),
                  a(href("#"), cls("more"), "Find out more")
                )
              )
            )
          ),
        )
      )

/*
			<div class="row mb-5">
				<div class="col-lg-7" data-aos="fade-up">
					<span class="subheading mb-2 d-inline-block">Other Interests</span>
					<h2>You May Also be Interested</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-lg-4 mb-4" data-aos="fade-up" data-aos-delay="100">
					<a href="#"><img src="medio/images/img_v_3-min.jpg" alt="Image" class="img-fluid rounded mb-4"></a>
					<h3 class="h4 mb-3"><a href="#">Digital Advertising</a></h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum consequuntur, iure quidem officiis adipisci</p>
					<p class="mb-0"><a href="#" class="more">Find out more</a></p>
				</div>
				<div class="col-md-6 col-lg-4 mb-4" data-aos="fade-up" data-aos-delay="200">
					<a href="#"><img src="medio/images/img_v_4-min.jpg" alt="Image" class="img-fluid rounded mb-4"></a>
					<h3 class="h4 mb-3"><a href="#">Content Creation</a></h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum consequuntur, iure quidem officiis adipisci</p>
					<p class="mb-0"><a href="#" class="more">Find out more</a></p>
				</div>
				<div class="col-md-6 col-lg-4 mb-4" data-aos="fade-up" data-aos-delay="300">
					<a href="#"><img src="medio/images/img_v_6-min.jpg" alt="Image" class="img-fluid rounded mb-4"></a>
					<h3 class="h4 mb-3"><a href="#">Time and Project Mgt.</a></h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum consequuntur, iure quidem officiis adipisci</p>
					<p class="mb-0"><a href="#" class="more">Find out more</a></p>
				</div>
			</div>
		</div>
	</div>
*/

}
