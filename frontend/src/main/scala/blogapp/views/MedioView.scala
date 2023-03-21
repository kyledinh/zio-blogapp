package blogapp.views

import com.raquo.laminar.api.L.{*, given}
import com.raquo.domtypes.generic.codecs.StringAsIsCodec // Moved in future version above 0.14.2
import com.raquo.laminar.keys.ReactiveHtmlAttr

import blogapp.Component

final case class MedioView() extends Component {
  val body: Div =
    div(
      div(cls("section bg-light"),
        div(cls("container"),
          h2("Medio Template"),
          p("A fullstack, idiomatic web app that serves as an example for best utilizing ZIO and the libraries within its ecosystem."),
          p("This version of the website uses Scala 3!"),
        )
      ),
      OtherInterests(),
      SubscribeToNewsletter(),
    )

  private def bodyLink(name: String, url: String) =
    a(
      cls("text-orange-700 hover:text-orange-600 text-l cursor-pointer"),
      target("_blank"),
      name,
      href(url)
    )

}


final case class OtherInterests() extends Component {

  val attrDataAos: ReactiveHtmlAttr[String] = customHtmlAttr("data-aos", StringAsIsCodec)
  val attrDataAosDelay: ReactiveHtmlAttr[String] = customHtmlAttr("data-aos-delay", StringAsIsCodec)

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
                  a(href("#"), "Content Crreation")
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


final case class SubscribeToNewsletter() extends Component {

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


final case class MedioDemoView() extends Component {

  val attrDataAos: ReactiveHtmlAttr[String] = customHtmlAttr("data-aos", StringAsIsCodec)
  val attrDataAosDelay: ReactiveHtmlAttr[String] = customHtmlAttr("data-aos-delay", StringAsIsCodec)

  val body: Div = 
    div(cls("col-lg-4 mb-4 mb-lg-0"),
      attrDataAos("fade-up"),
      attrDataAosDelay("0"), // data-aos="fade-up" data-aos-delay="0" : Medio theme
    ) 


/*
	<div class="hero v-h-full overlay"> 
		<div class="img-bg rellax">
			<img src="images/hero_1.jpg" alt="Image" class="img-fluid">
		</div>
		<div class="container">
			<div class="row align-items-center v-h-full">

				<div class="col-lg-10 col-xxl-6">
					<h1 class="heading-2 text-white mb-5" data-aos="fade-up">We are creative agency by Untree.co</h1>
					<p data-aos="fade-up" class="d-flex" data-aos-delay="200"><a href="#" class="btn btn-primary-outline white me-4">See Portfolio</a> <a href="https://www.youtube.com/watch?v=mwtbEGNABWU" class="glightbox d-inline-flex align-items-center playvid"><span class="icon-play me-2"></span><span>Watch our video</span></a></p>
				</div>

			</div>
		</div> 
  
  </div>

	<div class="section bg-light">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 mb-4 mb-lg-0" data-aos="fade-up" data-aos-delay="0">
					<div class="service grayscale">
						<div class="service-img">
							<a href="#"><img src="medio/images/img_1.jpg" alt="Image" class="img-fluid"></a>
						</div>
						<div class="service-inner">
							<span></span>
							<h3>Collaborate</h3>
							<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
							<p class="mb-0"><a href="#" class="more">Learn more</a></p>
						</div>

					</div>
				</div>
				<div class="col-lg-4 mb-4 mb-lg-0" data-aos="fade-up" data-aos-delay="100">
					<div class="service grayscale">
						<div class="service-img">
							<a href="#"><img src="medio/images/img_2.jpg" alt="Image" class="img-fluid"></a>
						</div>
						<div class="service-inner">
							<span></span>
							<h3>Connect</h3>
							<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
							<p class="mb-0"><a href="#" class="more">Learn more</a></p>
						</div>

					</div>
				</div>
				<div class="col-lg-4 mb-4 mb-lg-0" data-aos="fade-up" data-aos-delay="200">
					<div class="service grayscale">
						<div class="service-img">
							<a href="#"><img src="medio/images/img_3.jpg" alt="Image" class="img-fluid"></a>
						</div>
						<div class="service-inner">
							<span></span>
							<h3>Grow</h3>
							<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
							<p class="mb-0"><a href="#" class="more">Learn more</a></p>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row justify-content-center mb-5">
				<div class="col-lg-6 text-center" data-aos="fade-up" data-aos-delay="100">
					<span class="subheading mb-2 d-inline-block">Awesome Portfolio</span>
					<h2 class="mb-4">Our Portfolio</h2>
				</div>
			</div>

			<div class="row">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
							<a href="medio/images/img_v_1-min.jpg" class="media-1 glightbox">
								<img src="medio/images/img_v_1-min.jpg" alt="Image" class="img-fluid">
								<div class="media-1-content">
									<h2>Bonzai Tree</h2>
									<span class="category">Web Application</span>
								</div>
							</a>
						</div>
						<div class="col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="300">
							<a href="medio/images/img_v_2-min.jpg" class="media-1 glightbox">
								<img src="medio/images/img_v_2-min.jpg" alt="Image" class="img-fluid">
								<div class="media-1-content">
									<h2>Simple Woman</h2>
									<span class="category">Branding</span>
								</div>
							</a>
						</div>
						<div class="col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="400">
							<a href="medio/images/img_v_3-min.jpg" class="media-1 glightbox">
								<img src="medio/images/img_v_3-min.jpg" alt="Image" class="img-fluid">
								<div class="media-1-content">
									<h2>Fruits</h2>
									<span class="category">Website</span>
								</div>
							</a>
						</div>

						<div class="col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
							<a href="medio/images/img_v_4-min.jpg" class="media-1 glightbox">
								<img src="medio/images/img_v_4-min.jpg" alt="Image" class="img-fluid">
								<div class="media-1-content">
									<h2>Design Material</h2>
									<span class="category">Web Application</span>
								</div>
							</a>
						</div>
						<div class="col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
							<a href="medio/images/img_v_5-min.jpg" class="media-1 glightbox">
								<img src="medio/images/img_v_5-min.jpg" alt="Image" class="img-fluid">
								<div class="media-1-content">
									<h2>Handy Food</h2>
									<span class="category">Branding</span>
								</div>
							</a>
						</div>
						<div class="col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="300">
							<a href="medio/images/img_v_6-min.jpg" class="media-1 glightbox">
								<img src="medio/images/img_v_6-min.jpg" alt="Image" class="img-fluid">
								<div class="media-1-content">
									<h2>Cat With Cup</h2>
									<span class="category">Website</span>
								</div>
							</a>
						</div>


					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 pe-lg-5" data-aos="fade-up">
					<span class="subheading d-inline-block mb-2">We love to design</span>
					<h2 class="mb-4">Create Outstanding Website</h2>
					<p class="mb-5">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
					<p><a href="#" class="btn btn-black">Get started</a></p>
				</div>
				<div class="col-lg-8">
					<div class="row">
						<div class="col-6 col-lg-6 mt-5" data-aos="fade-up" data-aos-delay="100">
							<img src="medio/images/img_v_5-min.jpg" alt="Image" class="img-fluid rounded">
						</div>
						<div class="col-6 col-lg-6" data-aos="fade-up" data-aos-delay="200">
							<img src="medio/images/img_v_6-min.jpg" alt="Image" class="img-fluid rounded">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="section bg-light">
		<div class="container">
			<div class="row mb-5">
				<div class="col-12 text-center" data-aos="fade-up">
					<span class="subheading d-inline-block mb-2">Our Services</span>
					<h2>What we can do for you?</h2>
				</div>
			</div>
			<div class="row align-items-stretch">
				<div class="col-md-6 col-lg-4 mb-4 mb-lg-5" data-aos="fade-up">
					<div class="unit-4 d-flex">
						<div class="unit-4-icon me-4"><span class="feather-briefcase"></span></div>
						<div>
							<h3>Web Design</h3>
							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis quis molestiae vitae eligendi at.</p>
							<p><a href="#" class="more">Learn more</a></p>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-lg-4 mb-4 mb-lg-5" data-aos="fade-up" data-aos-delay="100">
					<div class="unit-4 d-flex">
						<div class="unit-4-icon me-4"><span class="feather-command"></span></div>
						<div>
							<h3>Option</h3>
							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis quis molestiae vitae eligendi at.</p>
							<p><a href="#" class="more">Learn more</a></p>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-lg-4 mb-4 mb-lg-5" data-aos="fade-up" data-aos-delay="200">
					<div class="unit-4 d-flex">
						<div class="unit-4-icon me-4"><span class="feather-cpu"></span></div>
						<div>
							<h3>Settings</h3>
							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis quis molestiae vitae eligendi at.</p>
							<p><a href="#" class="more">Learn more</a></p>
						</div>
					</div>
				</div>


				<div class="col-md-6 col-lg-4 mb-4 mb-lg-5" data-aos="fade-up" data-aos-delay="300">
					<div class="unit-4 d-flex">
						<div class="unit-4-icon me-4"><span class="feather-home"></span></div>
						<div>
							<h3>Settings</h3>
							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis quis molestiae vitae eligendi at.</p>
							<p><a href="#" class="more">Learn more</a></p>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-lg-4 mb-4 mb-lg-5" data-aos="fade-up" data-aos-delay="400">
					<div class="unit-4 d-flex">
						<div class="unit-4-icon me-4"><span class="feather-feather"></span></div>
						<div>
							<h3>Writing</h3>
							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis quis molestiae vitae eligendi at.</p>
							<p><a href="#" class="more">Learn more</a></p>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-lg-4 mb-4 mb-lg-5" data-aos="fade-up" data-aos-delay="500">
					<div class="unit-4 d-flex">
						<div class="unit-4-icon me-4"><span class="feather-file-text"></span></div>
						<div>
							<h3>Mobile Apps</h3>
							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis quis molestiae vitae eligendi at.</p>
							<p><a href="#" class="more">Learn more</a></p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row mb-5">
				<div class="col-md-6" data-aos="fade-up">
					<span class="subheading mb-2 d-inline-block">Testimonials</span>
					<h2>What's Our Client Think...</h2>
				</div>
			</div>

			<div class="testimonial-wrap" data-aos="fade-up"  data-aos-delay="100">
				<div id="testimonial">
					<div class="item">
						<div class="testmonial">
							<span class="quote">&rdquo;</span>
							<blockquote>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum consequuntur, iure quidem officiis adipisci, error odit omnis consequatur doloremque nobis. Quam vitae omnis perferendis excepturi?</p>
							</blockquote>
							<div class="d-flex author align-items-center">
								<div class="pic">
									<img src="medio/images/person_1.jpg" alt="Image" class="img-fluid">
								</div>
								<div class="author-name">
									<h2>Jon Smith</h2>
									<span>CEO, Co-Founder XYZ Inc.</span>
								</div>
							</div>
						</div>
					</div>

					<div class="item">
						<div class="testmonial">
							<span class="quote">&rdquo;</span>
							<blockquote>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum consequuntur, iure quidem officiis adipisci, error odit omnis consequatur doloremque nobis. Quam vitae omnis perferendis excepturi?</p>
							</blockquote>
							<div class="d-flex author align-items-center">
								<div class="pic">
									<img src="medio/images/person_2.jpg" alt="Image" class="img-fluid">
								</div>
								<div class="author-name">
									<h2>Jon Smith</h2>
									<span>CEO, Co-Founder XYZ Inc.</span>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>


	<div class="section mb-5">
		<div class="container">
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


	<div class="section section-contact m-md-5 px-md-5">
		<div class="container">
			<h2>Subscribe to Newsletter</h2>
			<p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Qui, exercitationem!</p>

			<form action="" class="form-contact">
				<div class="row">
					<div class="col-sm-4 col-md-4 col-lg-4">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Your name">
						</div>	
					</div>
					<div class="col-sm-4 col-md-4 col-lg-4">
						<div class="form-group">
							<input type="email" class="form-control" placeholder="Your email">
						</div>
					</div>

					<div class="col-sm-4 col-md-4 col-lg-4">
						<input type="submit" class="btn btn-black btn-block" value="Subscribe">
					</div>

				</div>
			</form>
		</div>	
	</div>


*/

}