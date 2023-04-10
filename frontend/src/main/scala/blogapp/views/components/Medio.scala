package blogapp.views.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr

object Medio {
  def attrDataAos: HtmlAttr[String]      = customHtmlAttr("data-aos", StringAsIsCodec)
  def attrDataAosDelay: HtmlAttr[String] = customHtmlAttr("data-aos-delay", StringAsIsCodec)

  def attrDataToggle: HtmlAttr[String] = customHtmlAttr("data-toggle", StringAsIsCodec)
  def attrDataTarget: HtmlAttr[String] = customHtmlAttr("data-target", StringAsIsCodec)
}
