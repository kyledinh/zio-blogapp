package blogapp.views

import blogapp.Component
import blogapp.models.*
import blogapp.Page.*
import blogapp.views.components.Components
import blogapp.views.components.Medio.{attrDataAos, attrDataAosDelay}

import blogapp.Requests

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.modifiers.RenderableNode
import com.raquo.laminar.nodes.ChildNode

import java.time.LocalDate
import scala.collection.immutable

final case class EditableScrawlView(scrawl: Scrawl, reload: () => Unit) extends Component with RenderableNode[Div] {

  val isEditingVar = Var(false)
  val rand         = new scala.util.Random

  val body: Div =
    div(
      cls := "card mb-3",
      h5(s"${scrawl.title}"),
      img(src := "simplex/images/scrawl-image-" + rand.between(1, 4) + ".jpg"),
      div(cls := "card-body", div(cls := "service-inner", p(s"${scrawl.body}")))
    )

  def asNode(value: com.raquo.laminar.api.L.Div): com.raquo.laminar.nodes.ChildNode.Base               = body
  def asNodeSeq(values: Seq[com.raquo.laminar.api.L.Div]): Seq[com.raquo.laminar.nodes.ChildNode.Base] = Seq(body)
  def asNodeIterable(values: Iterable[com.raquo.laminar.api.L.Div]): Iterable[com.raquo.laminar.nodes.ChildNode.Base] =
    Iterable(body)
  def asNodeOption(value: Option[com.raquo.laminar.api.L.Div]): Option[com.raquo.laminar.nodes.ChildNode.Base] = Some(
    body
  )

}

object EditableScrawlView {
  implicit val renderable: RenderableNode[EditableScrawlView] = new RenderableNode[EditableScrawlView] {
    override def asNode(value: EditableScrawlView): ChildNode.Base                              = value.body
    override def asNodeSeq(values: Seq[EditableScrawlView]): Seq[ChildNode.Base]                = values.map(_.body)
    override def asNodeIterable(values: Iterable[EditableScrawlView]): Iterable[ChildNode.Base] = values.map(_.body)
    override def asNodeOption(value: Option[EditableScrawlView]): Option[ChildNode.Base]        = value.map(_.body)
  }
}

final case class BoardView() extends Component {

  // Bus and Signals/Streams
  val reloadScrawlBus: EventBus[Unit] =
    new EventBus[Unit]

  val $scrawls: Signal[List[Scrawl]] =
    reloadScrawlBus.events
      .flatMap { _ =>
        Requests.getAllScrawls()
      }
      .toSignal(List.empty)

  reloadScrawlBus.emit(())

  // Elements
  val body: Div = div(
    reloadScrawlBus.events --> { _ => () },
    onMountCallback(_ => reloadScrawlBus.emit(())),
    div(
      cls := "bs-docs-section clearfix",
      div(cls := "container", h2("Scrawl Board II"), div(Components.formatDate(LocalDate.now())), br()),
      div(
        cls := "container",
        div(
          cls := "row row-cols-md-2 row-cols-lg-3",
          children <-- $scrawls.map { scrawls =>
            scrawls.map(EditableScrawlView(_, () => reloadScrawlBus.emit(())))
          }
        )
      ),
      div(
        cls := "section bg-light",
        div(
          cls := "container",
          "Scrawl Board showing all recent Scrawls!"
        )
      )
    )
  )

  private def bodyLink(name: String, url: String) =
    a(cls := "text-orange-700 hover:text-orange-600 text-l cursor-pointer", target := "_blank", name, href(url))
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
