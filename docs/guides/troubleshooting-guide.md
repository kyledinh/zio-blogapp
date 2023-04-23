# Troubleshooting Guide

## Command Commands

> The make targets are alias to bash scripts you can find in `scripts/*` directories. 

- `make check` - This will check installation of required software and provide version information
- `make postgres-check` - Will peak into your database and return some data from your tables
- `make backend-check` - Will send requests to your backend endpoints to see if they return a json response
- `make status` - Will check if sbtn is running Backend(Zio) and Frontend(Laminar)
- `docker ps` - Will list all running containers, check if you database is up

## Common Problems
 
- Docker is not running - `docker ps`
- Postges container is not running - `make postgres-check`
- Backend API, Frontend is not running - `make status`  
- Postgres tables are not created/migrated
- Config error between Backend and Postgres
- DNS/URL errors between Backend, Frontend and Postgres 
- Dependencies' version incompatibilty - (look in /build.sbt)

## Historical Problems
