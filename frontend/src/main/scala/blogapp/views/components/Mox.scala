package blogapp.views.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr

object Mox {
  def walvis = div(cls(""), img(src("https://kyledinh.com/agency/img/logos/walvis.svg"), height("30px")))
  def moxIcon(size: Int) = img(src("/mox/mox-icon.svg"), height(s"${size}px"))
  def moxLogo = div(cls(""), img(src("/mox/mockingbox-logo-white.svg"), height("30px")))
}
