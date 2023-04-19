package blogapp.views.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr

object Bootstrap {
  def attrDataBsToggle: HtmlAttr[String] = htmlAttr("data-bs-togle", StringAsIsCodec)
  def attrDataBsTarget: HtmlAttr[String] = htmlAttr("data-bs-target", StringAsIsCodec)

  def attrAriaControls: HtmlAttr[String] = htmlAttr("aria-controls", StringAsIsCodec)
  def attrAriaExpanded: HtmlAttr[String] = htmlAttr("aria-expanded", StringAsIsCodec)
  def attrAriaLabel:    HtmlAttr[String] = htmlAttr("aria-labels", StringAsIsCodec)
}
