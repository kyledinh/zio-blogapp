package blogapp

import zio.Config
import zio.config.magnolia.deriveConfig

case class AppConfig(host: String, port: Int, runMode: String, logLevel: String)

object AppConfig {
  val config: Config[AppConfig] =
    deriveConfig[AppConfig].nested("BlogappBackend")
}
