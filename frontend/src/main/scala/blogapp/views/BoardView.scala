package blogapp.views

import animus.*
import com.raquo.laminar.api.L.{*, given}
import blogapp.Component
import blogapp.models.*
import blogapp.views.components.{Components}
import blogapp.{Requests}
import java.time.LocalDate 

final case class EditableScrawlView(scrawl: Scrawl, reload: () => Unit) extends Component {
  val isEditingVar = Var(false)

  val body: Div = 
    div(cls("col-lg-4 mb-4 mb-lg-0"),
        // data-aos="fade-up" data-aos-delay="0",
      div(cls("service grayscale"),
        div(cls("service-img"),
          img(src("medio/images/img_1.jpg"))
        ),
        div(cls("servicce-inner"),
          h3(s"${scrawl.title}"),
          p(s"${scrawl.body}")
        )
      )
    // ).amend(
    //   data-aos("fade-up"),
    //   data-aos-delay("0")
    ) 

}

final case class BoardView() extends Component {

  // Bus and Signals/Streams
  val reloadScrawlBus: EventBus[Unit] =
    new EventBus[Unit] 

  val $scrawls: Signal[List[Scrawl]] =
    reloadScrawlBus.events.flatMap { _ =>
        Requests.getAllScrawls()
      }
      .toSignal(List.empty)

  reloadScrawlBus.emit(())

  // Elements
  val body: Div = div(
    reloadScrawlBus.events --> { _ => () },
    onMountCallback(_ => reloadScrawlBus.emit(())),      
    div(cls("text-l text-justify m-5"),
      div(cls("mb-2 text-xl text-orange-700 font-bold"),
        "Scrawl Board"
      ),
      div(cls("mb-2 text-xl text-gray-400 font-bold"),
        Components.formatDate(LocalDate.now())
      ),
      div(cls("section bg-light"),
        div(cls("container"),
          div(cls("row"),
            children <-- $scrawls.map { scrawls =>
              scrawls.map(EditableScrawlView(_, () => reloadScrawlBus.emit(())))
            }
          )
        )
      ),
      div(cls("mb-4"),
        "Scrawl Board showing all recent Scrawls!"
      ),
      div(cls("mb-4"),
        img(src("https://kyledinh.com/agency/img/logos/walvis.svg"),
          height("30px")
        )
      )
    )
  )

  private def bodyLink(name: String, url: String) =
    a(cls("text-orange-700 hover:text-orange-600 text-l cursor-pointer"),
      target("_blank"),
      name,
      href(url)
    )
}


/*
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


			</div>
		</div>
	</div>
  */