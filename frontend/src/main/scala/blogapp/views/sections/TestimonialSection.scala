package blogapp.views

import com.raquo.laminar.api.L.{*, given}
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}

import blogapp.Component

final case class TestimonialSection() extends Component {


	val body: Div =
		div(cls("section"),
			div(cls("container"),
				div(cls("row mb-5"),
					div(cls("col-md-6 aos-init aos-animate"),
						attrDataAos("fade-up"),
						span(cls("subheading mb-2 d-inline-block"),
							"Testimonials"),
						h2("What's Our Client Think...")
					)
				),
				div(cls("testimonal-wrap aos-init aos-animate"), attrDataAos("fade-up"), attrDataAosDelay("100"),
					div(idAttr("testimonal"),
						div(cls("item tns-item"),
							div(cls("testimonial"),
								span(cls("quote"),
									"&rdquo;"
								),
								blockQuote(
									p("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum consequuntur, iure quidem officiis adipisci, error odit omnis consequatur doloremque nobis. Quam vitae omnis perferendis excepturi?")
								),
								div(cls("d-flex author align-items-center"),
									div(cls("pic"),
										img(cls("img-fluid"),
											src("medio/images/person_1.jpg"),
										)
									),
									div(cls("author-name"),
										h2("Jon Smith"),
										span("CEO, Co-Founder XYZ Inc.")
									)
								)
							)
						),
						div(cls("item tns-item"),
							div(cls("testimonial"),
								span(cls("quote"),
									"&rdquo;"
								),
								blockQuote(
									p("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum consequuntur, iure quidem officiis adipisci, error odit omnis consequatur doloremque nobis. Quam vitae omnis perferendis excepturi?")
								),
								div(cls("d-flex author align-items-center"),
									div(cls("pic"),
										img(cls("img-fluid"),
											src("medio/images/person_2.jpg"),
										)
									),
									div(cls("author-name"),
										h2("Jake Mackey"),
										span("COO, Co-Founder XYZ Inc.")
									)
								)
							)
						),						
					)
				)
			)


	// <div class="section">
	// 	<div class="container">
	// 		<div class="row mb-5">
	// 			<div class="col-md-6" data-aos="fade-up">
	// 				<span class="subheading mb-2 d-inline-block">Testimonials</span>
	// 				<h2>What's Our Client Think...</h2>
	// 			</div>
	// 		</div>

	// 		<div class="testimonial-wrap" data-aos="fade-up"  data-aos-delay="100">
	// 			<div id="testimonial">
	// 				<div class="item">
	// 					<div class="testmonial">
	// 						<span class="quote">&rdquo;</span>
	// 						<blockquote>
	// 							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum consequuntur, iure quidem officiis adipisci, error odit omnis consequatur doloremque nobis. Quam vitae omnis perferendis excepturi?</p>
	// 						</blockquote>
	// 						<div class="d-flex author align-items-center">
	// 							<div class="pic">
	// 								<img src="medio/images/person_1.jpg" alt="Image" class="img-fluid">
	// 							</div>
	// 							<div class="author-name">
	// 								<h2>Jon Smith</h2>
	// 								<span>CEO, Co-Founder XYZ Inc.</span>
	// 							</div>
	// 						</div>
	// 					</div>
	// 				</div>

	// 				<div class="item">
	// 					<div class="testmonial">
	// 						<span class="quote">&rdquo;</span>
	// 						<blockquote>
	// 							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum consequuntur, iure quidem officiis adipisci, error odit omnis consequatur doloremque nobis. Quam vitae omnis perferendis excepturi?</p>
	// 						</blockquote>
	// 						<div class="d-flex author align-items-center">
	// 							<div class="pic">
	// 								<img src="medio/images/person_2.jpg" alt="Image" class="img-fluid">
	// 							</div>
	// 							<div class="author-name">
	// 								<h2>Jon Smith</h2>
	// 								<span>CEO, Co-Founder XYZ Inc.</span>
	// 							</div>
	// 						</div>
	// 					</div>
	// 				</div>

	// 			</div>
	// 		</div>
	// 	</div>
	// </div>






		)


}
