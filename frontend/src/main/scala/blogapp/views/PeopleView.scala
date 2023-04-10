package blogapp.views

import com.raquo.laminar.api.L.{*, given}

import blogapp.Component
import blogapp.models.*
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}
import blogapp.Requests

final case class EditablePeopleView(person: Person, reload: () => Unit) extends Component {

  val isEditingVar = Var(false)
  val rand         = new scala.util.Random

  val body: Div =
    div(
      cls("col-lg-4 mb-4 mb-lg-0"),
      attrDataAos("fade-up"),
      attrDataAosDelay("100"), // data-aos="fade-up" data-aos-delay="0" : Medio theme
      div(
        cls("service grayscale"),
        div(
          cls("service-inner"),
          h3(s"${person.fullName}"),
          h5(s"${person.id}"),
          p(s"${person.email}"),
          a(cls("btn btn-black"), href(s"/person/${person.idString}"), "View")
        )
      )
    )
}

final case class PeopleView() extends Component {

  // Bus and Signals/Streams
  val reloadPeopleBus: EventBus[Unit] =
    new EventBus[Unit]

  val $persons: Signal[List[Person]] =
    reloadPeopleBus.events
      .flatMap { _ =>
        Requests.getAllPersons()
      }
      .toSignal(List.empty)

  reloadPeopleBus.emit(())

  val body: Div =
    div(
      cls("section bg-light"),
      div(
        cls("container"),
        reloadPeopleBus.events --> { _ => () },
        onMountCallback(_ => reloadPeopleBus.emit(())),
        h2("People : "),
        div(
          cls("row"),
          children <-- $persons.map { scrawls =>
            scrawls.map(EditablePeopleView(_, () => reloadPeopleBus.emit(())))
          }
        ),
        br(),
        p(
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, "
        ),
        p("Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore"),
        p("Excepteur sint occaecat cupidatat non proident")
      )
    )

  private def bodyLink(name: String, url: String) =
    a(
      cls("text-orange-700 hover:text-orange-600 text-l cursor-pointer"),
      target("_blank"),
      name,
      href(url)
    )
}
