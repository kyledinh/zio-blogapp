package blogapp.server

// import blogapp.Migrations
import zhttp.http.*
import zhttp.http.middleware.HttpMiddleware
import zhttp.service.Server
import zio.*
import blogapp.AppConfig

case class BackendConfig(port: Int)

final case class BlogServer(
    personRoutes: PersonRoutes,
    scrawlRoutes: ScrawlRoutes,
    adminRoutes: AdminRoutes
    // migrations: Migrations
) {

  val allRoutes: HttpApp[Any, Throwable] = {
    personRoutes.routes ++ scrawlRoutes.routes ++ adminRoutes.routes
  }

  /** Logs the requests made to the server. It also adds a request ID to the
    * logging context, so any further logging that occurs in the handler can be
    * associated with the same request. For more information on the logging,
    * see: https://zio.github.io/zio-logging/
    */
  val loggingMiddleware: HttpMiddleware[Any, Nothing] =
    new HttpMiddleware[Any, Nothing] {
      override def apply[R1 <: Any, E1 >: Nothing](
          http: Http[R1, E1, Request, Response]
      ): Http[R1, E1, Request, Response] =
        Http.fromOptionFunction[Request] { request =>
          Random.nextUUID.flatMap { requestId =>
            ZIO.logAnnotate("REQUEST-ID", requestId.toString) {
              for {
                _      <- ZIO.logInfo(s"Request: $request")
                result <- http(request)
              } yield result
            }
          }
        }
    }

  val backendConfig =
    ZLayer.fromZIO(
      ZIO.config[AppConfig](AppConfig.config).map { c =>
        BackendConfig(
          port = c.port
        )
      }
    )

  def start: ZIO[Any, Throwable, Unit] =
    for {
      // _    <- migrations.reset.repeat(Schedule.fixed(15.minutes)).fork
      port <- System.envOrElse("BLOGAPP_BACKEND_PORT", "4000").map(_.toInt)
      _    <- ZIO.logDebug("Backend running on port: " + port.toString())
      _    <- Server.start(port, allRoutes @@ Middleware.cors() @@ loggingMiddleware)
    } yield ()

}

object BlogServer {

  // val layer: ZLayer[PersonRoutes with ScrawlRoutes with Migrations, Nothing, BlogServer] =
  val layer: ZLayer[PersonRoutes with ScrawlRoutes with AdminRoutes, Nothing, BlogServer] =
    ZLayer.fromFunction(BlogServer.apply _)

}
