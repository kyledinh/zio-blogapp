package blogapp.views

import animus.*
import com.raquo.laminar.api.L.{*, given}

import blogapp.Component
import blogapp.models.*
import blogapp.views.components.{Components}
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}
import blogapp.{Requests}
import java.time.LocalDate 

final case class EditablePersonView(person: Person, reload: () => Unit) extends Component {

  val isEditingVar = Var(false)
  val rand = new scala.util.Random

  val body: Div = 

    div(cls("col-lg-4 mb-4 mb-lg-0"),
      attrDataAos("fade-up"),
      attrDataAosDelay("100"), // data-aos="fade-up" data-aos-delay="0" : Medio theme
      div(cls("service grayscale"),
        div(cls("service-img"),
          img(src("medio/images/img_" + rand.between(1,4) + ".jpg"))
        ),
        div(cls("service-inner"),
          h3(s"${person.fullName}"),
          p(s"${person.email}")
        )
      )
    ) 
}

final case class PersonDetailView(person: Person, reload: () => Unit) extends Component {

  val isEditingVar = Var(false)

  val body: Div = 

    div(cls("container"),
      attrDataAos("fade-up"),
      attrDataAosDelay("100"), // data-aos="fade-up" data-aos-delay="0" : Medio theme
      div(cls("service grayscale"),
        div(cls("service-inner"),
          h3(s"${person.fullName}"),
          p(s"${person.address}"),
          p(s"${person.email}")
        )
      )
    ) 
}


final case class PersonView(id: Uuid) extends Component {

  // Bus and Signals/Streams
  val reloadPersonBus: EventBus[Unit] =
    new EventBus[Unit] 

  val $person: EventStream[Person] =
    EventStream.merge(
      Requests.getPerson(id),
      reloadPersonBus.events.flatMap { _ => 
        Requests.getPerson(id)
      }
    )
      
  val reloadScrawlBus: EventBus[Unit] =
    new EventBus[Unit] 

  val $scrawls: Signal[List[Scrawl]] = reloadScrawlBus.events.flatMap { _ =>
        // Requests.getScrawlsByPerson(id)
        Requests.getAllScrawls()
      }
      .toSignal(List.empty)

  reloadScrawlBus.emit(())

  // Elements
  val body: Div = div(
    // reloadScrawlBus.events --> { _ => () },
    onMountCallback(_ => reloadScrawlBus.emit(())),      
    div(cls("section bg-light"),
      div(cls("container"),
        child <-- $person.map(PersonDetailView(_, () => reloadPersonBus.emit(())) 
      ),
        div(cls("container"),
          div(cls("row"),
            children <-- $scrawls.map { scrawls =>
              scrawls.map(EditableScrawlView(_, () => reloadScrawlBus.emit(())))
            }
          )
        ),
        div(cls("section bg-light"),
          div(cls("container"),
            h3("Scrawls:"),
            div(cls("row"),
              children <-- $scrawls.map { scrawls =>
                scrawls.map(EditableScrawlView(_, () => reloadScrawlBus.emit(())))
              }
            ),
            div(cls(""), img(src("https://kyledinh.com/agency/img/logos/walvis.svg"),height("30px")))
          )
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