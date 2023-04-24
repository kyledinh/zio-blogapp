# Architecture Guide

- Designed to use Scala3 for backend with ZIO and frontend with Laminar
- Backend ZIO app into container
- Frontend Laminar app into Nginx container
- Postgres database, Docker container in dev, RDS or other service in prod

## Local Dev
- Postgres in Docker container, localhost:5432
- Backend in stbn, localhost:4000
- Frontend in sbtn/vite, localhost:3000

## Local Kubernetes Cluster

## Kubernetes Cluster 

## Build Pipeline

## App Features
- Add person thru UI
- Edit person
- Add scrawl by person
- Edit/Update scrawls
- Like/Share scrawls

## Other Features
- User signup 
- Token Auth 
- Metrics 
- Logging