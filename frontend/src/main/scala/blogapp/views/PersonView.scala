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

final case class ScrawlDetailView(scrawl: Scrawl, reload: () => Unit) extends Component {

  val body: Div = 
    div(cls("container"),
      attrDataAos("fade-up"),
      attrDataAosDelay("100"), // data-aos="fade-up" data-aos-delay="0" : Medio theme
      div(cls("service grayscale"),
        div(cls("service-inner"),
          h3(s"${scrawl.title}"),
          p(s"${scrawl.personId}"),
          p(s"${scrawl.body}")
        )
      )
    ) 
}


final case class PersonView(id: Uuid) extends Component {

  // Bus and Signals/Streams
  val reloadPersonBus: EventBus[Unit] = new EventBus[Unit] 
  val reloadScrawlBus: EventBus[Unit] = new EventBus[Unit] 

  val $person: EventStream[Person] =
    EventStream.merge(
      Requests.getPerson(id),
      reloadPersonBus.events.flatMap { _ => 
        Requests.getPerson(id)
      }
    )
      
  val $scrawls: Signal[List[Scrawl]] = 
    // Requests.getScrawlsByPerson(id)
    // Requests.getAllScrawls()
    reloadScrawlBus.events.flatMap { _ => Requests.getScrawlsByPerson(id) }
      .toSignal(List.empty)

  reloadPersonBus.emit(())
  reloadScrawlBus.emit(())

  // Elements
  val body: Div = div(
    reloadPersonBus.events --> { _ => () },
    reloadScrawlBus.events --> { _ => () },
    onMountCallback(_ => reloadPersonBus.emit(())),
    onMountCallback(_ => reloadScrawlBus.emit(())),     
    div(cls("section bg-light"),
      div(cls("container"),
        h3("Person:"),
        child <-- $person.map(PersonDetailView(_, () => reloadPersonBus.emit(())))
      )
    ),
    div(cls("section bg-light"),
      div(cls("container"),
        h3("Scrawls:"),
        div(cls("row"),
          children <-- $scrawls.map { scrawl =>
            scrawl.map(ScrawlDetailView(_, () => reloadScrawlBus.emit(())))
          }
        ),
        div(cls(""), img(src("https://kyledinh.com/agency/img/logos/walvis.svg"),height("30px")))
      )
    )
  )

}
