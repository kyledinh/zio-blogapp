# ZIO Blog App: An sample application written with ZIO 2, Scala 3 and ScalaJS.

> This is repo is taking the zio/zio-petclinic project and building it with Scala 3.1.3

- Original repo: https://github.com/zio/zio-petclinic (Scala 2)
- Original video: https://www.youtube.com/watch?v=3lopiYfWmdQ (Awesome!)
- Then updated to Scala 3: https://github.com/kyledinh/zio-petclinic-scala-3 (blogapp branch) then used as template to create this Blog App

### Changes from the original repo with:

- [x] Update from Scala 2 to Scala 3.1.3
- [x] Docker for Postgres Database
- [x] Backend API for Blog App
- [ ] Frontend with Scala.js
- [ ] Update Tests
- [ ] Tools 
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

To run the backend API server (inside sbt shell)
```
sbt 
~ backend/reStart

Crtl+c (to exit)
```

Run the frontend in a separate **Terminal 2**
```
open http://localhost:3000
make frontend-up
```

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


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[product-screenshot]: js-frontend/zio-pet-clinic-webpage.png