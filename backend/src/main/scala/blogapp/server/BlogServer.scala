package blogapp.server

import blogapp.Migrations
import zhttp.http.*
import zhttp.http.middleware.HttpMiddleware
import zhttp.service.Server
import zio.*

final case class BlogServer(
    personRoutes: PersonRoutes,
    scrawlRoutes: ScrawlRoutes,
    migrations: Migrations
) {

  val allRoutes: HttpApp[Any, Throwable] = {
    personRoutes.routes ++ scrawlRoutes.routes 
  }

  /** Logs the requests made to the server.
    * It also adds a request ID to the logging context, so any further logging
    * that occurs in the handler can be associated with the same request.
    * For more information on the logging, see:
    * https://zio.github.io/zio-logging/
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

  /** Resets the database to the initial state every 15 minutes to clean up the
    * deployed Heroku data. Then, it obtains a port from the environment on
    * which to start the server. In the case of being run in production, the
    * port will be provided by Heroku, otherwise the port will be 8080. The
    * server is then started on the given port with the routes provided.
    */
  def start: ZIO[Any, Throwable, Unit] =
    for {
      _    <- migrations.reset.repeat(Schedule.fixed(15.minutes)).fork
      port <- System.envOrElse("PORT", "8080").map(_.toInt)
      _    <- Server.start(port, allRoutes @@ Middleware.cors() @@ loggingMiddleware)
    } yield ()

}

object BlogServer {

  val layer: ZLayer[PersonRoutes with ScrawlRoutes with Migrations, Nothing, BlogServer] =
    ZLayer.fromFunction(BlogServer.apply _)

}
