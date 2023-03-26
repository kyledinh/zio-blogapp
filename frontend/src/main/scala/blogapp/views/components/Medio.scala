package blogapp.views.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.domtypes.generic.codecs.StringAsIsCodec // Moved in future version above 0.14.2
import com.raquo.laminar.keys.ReactiveHtmlAttr


object Medio {
  def attrDataAos: ReactiveHtmlAttr[String] = customHtmlAttr("data-aos", StringAsIsCodec)
  def attrDataAosDelay: ReactiveHtmlAttr[String] = customHtmlAttr("data-aos-delay", StringAsIsCodec)

  def attrDataToggle: ReactiveHtmlAttr[String] = customHtmlAttr("data-toggle", StringAsIsCodec)
  def attrDataTarget: ReactiveHtmlAttr[String] = customHtmlAttr("data-target", StringAsIsCodec) 
}
