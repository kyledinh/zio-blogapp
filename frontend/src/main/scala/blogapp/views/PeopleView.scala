package blogapp.views

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.modifiers.RenderableNode
import com.raquo.laminar.nodes.ChildNode

import blogapp.Component
import blogapp.models.*
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}
import blogapp.views.EditablePeopleView.renderable
import blogapp.Requests

case class EditablePeopleView(person: Person, reload: () => Unit) extends Component with RenderableNode[Div] {

  val isEditingVar = Var(false)
  val rand         = new scala.util.Random

  val body: Div =
    div(
      a(
        cls("list-group-item list-group-item-action d-flex gap-3 py-3 "),
        href(s"/person/${person.idString}"),
        img(cls("rounded-circle flex-shrink-0"), src("https://github.com/twbs.png"), widthAttr(32), heightAttr(32)),
        div(
          cls("d-flex gap-2 w-100 justify-content-between"),
          h5(s"${person.fullName}"),
          h6(s"${person.id}"),
          p(s"${person.email}")
        )
      )
    )

  def asNode(value: com.raquo.laminar.api.L.Div): com.raquo.laminar.nodes.ChildNode.Base               = body
  def asNodeSeq(values: Seq[com.raquo.laminar.api.L.Div]): Seq[com.raquo.laminar.nodes.ChildNode.Base] = Seq(body)
  def asNodeIterable(values: Iterable[com.raquo.laminar.api.L.Div]): Iterable[com.raquo.laminar.nodes.ChildNode.Base] =
    Iterable(body)
  def asNodeOption(value: Option[com.raquo.laminar.api.L.Div]): Option[com.raquo.laminar.nodes.ChildNode.Base] = Some(
    body
  )
}

object EditablePeopleView {
  implicit val renderable: RenderableNode[EditablePeopleView] = new RenderableNode[EditablePeopleView] {
    override def asNode(value: EditablePeopleView): ChildNode.Base                              = value.body
    override def asNodeSeq(values: Seq[EditablePeopleView]): Seq[ChildNode.Base]                = values.map(_.body)
    override def asNodeIterable(values: Iterable[EditablePeopleView]): Iterable[ChildNode.Base] = values.map(_.body)
    override def asNodeOption(value: Option[EditablePeopleView]): Option[ChildNode.Base]        = value.map(_.body)
  }
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
          cls("list-group"),
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
