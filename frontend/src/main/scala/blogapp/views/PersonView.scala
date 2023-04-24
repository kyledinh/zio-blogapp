package blogapp.views

import animus.*
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.modifiers.RenderableNode
import com.raquo.laminar.nodes.ChildNode

import blogapp.Component
import blogapp.models.*
import blogapp.views.components.Components
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}
import blogapp.views.ScrawlDetailView.sdvRenderable
import blogapp.Requests

import java.time.LocalDate

final case class EditablePersonView(person: Person, reload: () => Unit) extends Component {

  val isEditingVar = Var(false)
  val rand         = new scala.util.Random

  val body: Div =
    div(
      cls("col-lg-4 mb-4 mb-lg-0"),
      attrDataAos("fade-up"),
      attrDataAosDelay("100"), // data-aos="fade-up" data-aos-delay="0" : Medio theme
      div(
        cls("service grayscale"),
        div(cls("service-img"), img(src("medio/images/img_" + rand.between(1, 4) + ".jpg"))),
        div(cls("service-inner"), h3(s"${person.fullName}"), p(s"${person.email}"))
      )
    )
}

final case class PersonDetailView(person: Person, reload: () => Unit) extends Component {

  val isEditingVar = Var(false)

  val body: Div =
    div(
      cls("row featurette"),
      div(
        cls("col-md-7 order-md-2"),
        h5(s"${person.fullName}"),
        p(s"${person.id}"),
        p(s"${person.address}"),
        p(s"${person.email}"),
        a(cls("btn btn-black"), href(s"#"), "Edit")
      ),
      div(
        cls("col-md-5 order-md-1"),
        img(
          cls("featurette-image img-fluid mx-auto"),
          // data-src="holder.js/500x500/auto"
          // alt="500x500"
          src(
            "data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22500%22%20height%3D%22500%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20500%20500%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_187a05b9c21%20text%20%7B%20fill%3A%23AAAAAA%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A25pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_187a05b9c21%22%3E%3Crect%20width%3D%22500%22%20height%3D%22500%22%20fill%3D%22%23EEEEEE%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22185.12109375%22%20y%3D%22261.1%22%3E500x500%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E"
          ),
          // data-holder-rendered="true",
          styleAttr("width: 240px; height: 240px;")
        )
      )
    )
}
// https://getbootstrap.com/docs/4.0/examples/carousel/

class ScrawlDetailView(scrawl: Scrawl, reload: () => Unit) extends Component with RenderableNode[Div] {

  val body: Div =
    div(
      a(
        cls("list-group-item list-group-item-action d-flex gap-3 py-3 "),
        img(cls("rounded-circle flex-shrink-0"), src("https://github.com/twbs.png"), widthAttr(32), heightAttr(32)),
        div(
          cls("d-flex gap-2 w-100 justify-content-between"),
          h5(s"${scrawl.title}"),
          h6(s"${scrawl.id}"),
          p(s"${scrawl.body}")
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

object ScrawlDetailView {
  implicit val sdvRenderable: RenderableNode[ScrawlDetailView] = new RenderableNode[ScrawlDetailView] {
    override def asNode(value: ScrawlDetailView): ChildNode.Base                              = value.body
    override def asNodeSeq(values: Seq[ScrawlDetailView]): Seq[ChildNode.Base]                = values.map(_.body)
    override def asNodeIterable(values: Iterable[ScrawlDetailView]): Iterable[ChildNode.Base] = values.map(_.body)
    override def asNodeOption(value: Option[ScrawlDetailView]): Option[ChildNode.Base]        = value.map(_.body)
  }
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
    reloadScrawlBus.events
      .flatMap(_ => Requests.getScrawlsByPerson(id))
      .toSignal(List.empty)

  reloadPersonBus.emit(())
  reloadScrawlBus.emit(())

  // Elements
  val body: Div = div(
    reloadPersonBus.events --> { _ => () },
    reloadScrawlBus.events --> { _ => () },
    onMountCallback(_ => reloadPersonBus.emit(())),
    onMountCallback(_ => reloadScrawlBus.emit(())),
    div(
      cls("section bg-light"),
      div(cls("container"), h3("Person:"), child <-- $person.map(PersonDetailView(_, () => reloadPersonBus.emit(()))))
    ),
    div(padding("20px 0px")),
    div(
      cls("section bg-light"),
      div(
        cls("container"),
        h3("Scrawls:"),
        div(
          cls("list-group"),
          children <-- $scrawls.map { scrawls =>
            scrawls.map(ScrawlDetailView(_, () => reloadScrawlBus.emit(())))
          }
        )
      )
    )
  )

}
