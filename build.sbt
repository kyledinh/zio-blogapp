import scala.io.Source

val semVersion = Source.fromFile("sem-version").getLines.toList.head

ThisBuild / scalaVersion     := "3.2.2"    // "3.1.3" "2.13.8"
ThisBuild / version          := semVersion // "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.kyledinh"
ThisBuild / organizationName := "kyledinh"

val animusVersion               = "0.2.2"      // "0.1.15"
val flywayVersion               = "9.4.0"      // "8.5.12"
val laminarVersion              = "15.0.1"     // "0.14.2"
val postgresVersion             = "42.3.6"
val slf4jVersion                = "1.7.36"
val zioConfigVersion            = "4.0.0-RC14" 
val zioHttpVersion              = "2.0.0-RC11" // "2.0.0-RC9"
val zioJsonVersion              = "0.3.0"      // "0.3.0-RC8"
val zioLoggingVersion           = "2.1.1"      // "2.0.0-RC10"
val zioQuillVersion             = "4.5.0"      // "4.0.0-RC1"
val zioTestContainersVersion    = "0.6.0"
val zioVersion                  = "2.0.2"      // "2.0.0-RC6"
val zioMetricsConnectorsVersion = "2.0.0-RC6"

Global / onChangedBuildSource := ReloadOnSourceChanges

val sharedSettings = Seq(
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding",
    "utf8",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-unchecked",
    "-Xfatal-warnings"
  )
)

lazy val root = (project in file("."))
  .aggregate(backend, frontend, shared.js, shared.jvm)
  .settings(name := "blogapp")

lazy val backend = (project in file("backend"))
  .settings(
    name := "blogapp-backend",
    libraryDependencies ++= Seq(
      "dev.zio"               %% "zio"                               % zioVersion,
      "dev.zio"               %% "zio-config"                        % zioConfigVersion,
      "dev.zio"               %% "zio-logging-slf4j"                 % zioLoggingVersion,
      "dev.zio"               %% "zio-macros"                        % zioVersion,
      "dev.zio"               %% "zio-metrics-connectors"            % zioMetricsConnectorsVersion,
      "dev.zio"               %% "zio-test"                          % zioVersion % Test,
      "dev.zio"               %% "zio-test-sbt"                      % zioVersion % Test,
      "io.d11"                %% "zhttp"                             % zioHttpVersion,
      "io.getquill"           %% "quill-jdbc-zio"                    % zioQuillVersion,
      "io.github.scottweaver" %% "zio-2-0-db-migration-aspect"       % zioTestContainersVersion,
      "io.github.scottweaver" %% "zio-2-0-testcontainers-postgresql" % zioTestContainersVersion,
      "org.flywaydb"           % "flyway-core"                       % flywayVersion,
      "org.postgresql"         % "postgresql"                        % postgresVersion,
      "org.slf4j"              % "slf4j-api"                         % slf4jVersion,
      "org.slf4j"              % "slf4j-simple"                      % slf4jVersion
    ),
    Test / fork := true,
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
  .enablePlugins(DockerPlugin)
  .enablePlugins(JavaAppPackaging)
  .settings(sharedSettings)
  .enablePlugins(FlywayPlugin)
  .settings(
    flywayUrl      := "jdbc:postgresql://localhost:5432/blogapp",
    flywayUser     := "postgres",
    flywayPassword := "password"
  )
  .dependsOn(shared.jvm)

lazy val frontend = (project in file("frontend"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "blogapp-frontend",
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    libraryDependencies ++= Seq(
      "com.raquo"                     %%% "laminar"         % laminarVersion,
      "io.github.kitlangton"          %%% "animus"          % animusVersion,
      "com.raquo"                     %%% "waypoint"        % "6.0.0", // "0.5.0",
      "io.github.cquiroz"             %%% "scala-java-time" % "2.4.0",
      "com.softwaremill.sttp.client3" %%% "core"            % "3.6.2",
      "dev.zio"                       %%% "zio-json"        % zioJsonVersion
    )
  )
  .settings(sharedSettings)
  .dependsOn(shared.js)

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("shared"))
  .settings(
    scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    libraryDependencies ++= Seq(
      "dev.zio" %%% "zio-json" % zioJsonVersion
    )
  )
  .jsConfigure(_.enablePlugins(ScalaJSPlugin))
