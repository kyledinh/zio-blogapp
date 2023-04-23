# Onboarding Guide
> This install guide is for *nix platforms; MacOS 12.1 Monterey perticularly

## Software Requirements 

Software       | Version        | Install
---------------|----------------|-----------------------------------------------
JVM            | openjdk 17.0.4 | https://sdkman.io/install
Scala          | 3.2.2          | https://www.scala-lang.org/download
sbt            | 1.8.2          | https://www.scala-sbt.org/download.html
Docker Desktop | 4.3.x          | https://www.docker.com/products/docker-desktop
make           |                | Xcode CLI for MacOS
node           | 14.15.1        | https://nodejs.org/en/download
npm            | 8.0.0          |
yarn           | 1.22.10        |


## Installation

- Check for dependencies - `make check` will display some vars for the project and check for required dependencies and versions
- Install Scala 3/JVM with the [Coursier](https://get-coursier.io/) tool, or follow [other options](https://dotty.epfl.ch/3.0.0/index.html) 
- Install sbt
- Install Docker
- Install make - Included in Xcode Command Line Tool - `xcode-select --install`
- Git software and access to repository

<br><hr><br>

## Build Local For Local Development

[Quick Dev Guide](../../README.md#quick-guide-to-localdev-with-docker) will have Make target(alias) to run

- Setup - `make setup` will create necessary local files and directories
- Compile backend - `make backend-compile`
- Compile frontend - `make frontend-compile`
- Run Postges database in Docker - `make postgres-up`
- Initial Migration for Postgres database - `make postgres-init-migrate`
- Run backend - `make backend-up`
- Run frontend - `make frontend-up`

### Checking Health of Local Development
- Run make commands to check - `make check`

<br><hr><br>

## VS Code Integration
- [Download VS Code](https://code.visualstudio.com/download)
- [Debugging Scala 3 in VS Code - YouTube](https://www.youtube.com/watch?v=zX-t5E5hQh0)
- [Intro Scala Dev using VS Code - Dick Wall](https://www.youtube.com/watch?v=tNLS6rOGBlo)

### Extensions 
- Scala Syntax (official)
- Scala (Metals) Extension
- Scala (sbt)
- PostgreSQL Extension
- PostgresSQL 
- Vim (optional)

<br><hr><br>

## IntelliJ
- TODO 
