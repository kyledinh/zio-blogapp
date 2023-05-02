package blogapp

import blogapp.server.*
import blogapp.services.*
import zio.*
import zio.logging.backend.SLF4J
import zio.logging.removeDefaultLoggers
// import zio.metrics.connectors.{MetricsConfig, newrelic}

object Main extends ZIOAppDefault {

  /** Configures Metrics to be run at a set interval, in our case every five
    * seconds
    */
  // val metricsConfig =
  //   ZLayer.succeed(MetricsConfig(5.seconds))

  override val run: Task[Unit] =
    ZIO
      .serviceWithZIO[BlogServer](_.start)
      .provide(
        BlogServer.layer,
        AdminRoutes.layer,
        ScrawlRoutes.layer,
        PersonRoutes.layer,
        QuillContext.dataSourceLayer,
        AdminServiceLive.layer,
        PersonServiceLive.layer,
        ScrawlServiceLive.layer,
        // Migrations.layer,
        SLF4J.slf4j,
        removeDefaultLoggers

        // newrelic.newRelicLayer,
        // newrelic.NewRelicConfig.fromEnvLayer,
        // metricsConfig
      )

}
