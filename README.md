# ZIO Blog App: An sample application written with ZIO 2, Scala 3 and ScalaJS.

> This is repo is taking the zio/zio-petclinic project and building it with Scala 3.1.3

- Original repo: https://github.com/zio/zio-petclinic (Scala 2)
- Original video: https://www.youtube.com/watch?v=3lopiYfWmdQ (Awesome!)
- Then updated to Scala 3: https://github.com/kyledinh/zio-petclinic-scala-3 (blogapp branch) then used as template to create this Blog App

### Changes from the original repo with:

- [x] Update from Scala 2 to Scala 3.1.3
- [x] Docker for Postgres Database
- [x] Backend API for Blog App
- [x] Frontend with Scala.js/Laminar (Apply a different predesigned theme)
- [ ] Update Tests
- [ ] Developer Tools 
- [ ] Deploy with Kubernetes 
- [ ] CI pipeline
- [ ] Metrics


<br><hr><br>

## Software Versions 

| Software       | Version        | Install                                        |
|----------------|----------------|------------------------------------------------|
| JVM            | openjdk 17.0.4 | https://sdkman.io/install                      |
| Scala          | 3.1.3          | https://www.scala-lang.org/download            |
| sbt            | 1.7.1          | https://www.scala-sbt.org/download.html        |  
| Zio            | 2.0.2          | https://zio.dev/getting_started                |
| Postgres       | 14             | https://hub.docker.com/_/postgres              |
| Docker Desktop | 4.3.x          | https://www.docker.com/products/docker-desktop |

<br><hr><br>

## Running localdev with Docker
> Requires Docker to be running  

### Setup

- `make check`
- `make setup`

Run the local database, start backend server in **Terminal 1**

Start the database
- `make postgres-up`
- `make postgres-init-migrate` (do this only on first time to seed the database)
- `make postgres-down` (to stop and remove the database container)

To run the backend API server (With sbt server)
- `make backend-compile`
- `make backend-up`

Run the frontend in a separate **Terminal 2**
- `make frontend-compile`
- `make frontend-up`
- `open http://localhost:3000`

Check processes that are running
- `make status`

<br>

> Port number for services  

| Service  | Default Port | Environment Var      | Usage                                    |
|----------|--------------|----------------------|------------------------------------------|
| frontend | :3000        |                      | http://localhost:3000/                   |
| backend  | :4000        | BLOGAPP_BACKEND_PORT | http://localhost:4000/scrawls            |
| postgres | :5432        |                      | jdbc:postgresql://localhost:5432/blogapp |

<br><hr><br>

## Screenshots 

[![Board Page Screenshot][board-page-screenshot]](./docs/assets/blogapp-board-screenshot.png)
[![People Page Screenshot][people-page-screenshot]](./docs/assets/blogapp-people-screenshot.png)
[![Medio Page Screenshot][medio-page-screenshot]](./docs/assets/blogapp-medio-screenshot.png)

<br><hr><br>

## Project Outline 

A good starting point to understand this repo is with the `Makefile`. It will have the most common commands to check, setup, compile and build the project.

```
.
├── LICENSE
├── Makefile                   Commands to build/develop this project 
├── README.md
├── backend                    Scala code for RESTful api 
│   └── src
│       ├── main
│       └── test
│
├── build.sbt                  Dependency manager 
├── docker_pg_vol              Docker volume for Postgres DB
├── frontend                   Scala code that generates `main.js` for the webserver
│   ├── src
│   │   └── main
│   └── target
│       └── scala-3.1.3        main.js will be generated here
│
├── js-frontend                Javasript webserver using vite/Node
│   ├── index.html
│   ├── main.js
│   ├── main.scss
│   ├── medio/                 CSS/JS UI Template folder, "Medio" theme
│   ├── node_modules/
│   ├── package.json
│   ├── postcss.config.js
│   ├── tailwind.config.js
│   ├── vite.config.js
│   └── yarn.lock
│
├── project                    The only project directory to be concerned with
│   ├── build.properties       sbt version  
│   └── plugins.sbt            plugins
│
├── sem-version                x.x.x
└── shared                     Scala code shared by backend and frontend, models 
    └── src
        ├── main
        └── test
```

## Response HTML Tempate 
- Medio - from [Untree.co](https://untree.co/free-templates/medio-web-design-agency-template-free-download/)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[product-screenshot]: js-frontend/zio-pet-clinic-webpage.png
[board-page-screenshot]: docs/assets/blogapp-board-screenshot.png
[people-page-screenshot]: docs/assets/blogapp-people-screenshot.png
[medio-page-screenshot]: docs/assets/blogapp-medio-screenshot.png