package blogapp.views.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr

object Medio {
  def attrDataAos: HtmlAttr[String]      = htmlAttr("data-aos", StringAsIsCodec)
  def attrDataAosDelay: HtmlAttr[String] = htmlAttr("data-aos-delay", StringAsIsCodec)

  def attrDataToggle: HtmlAttr[String] = htmlAttr("data-toggle", StringAsIsCodec)
  def attrDataTarget: HtmlAttr[String] = htmlAttr("data-target", StringAsIsCodec)
}
