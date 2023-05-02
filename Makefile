export SHELL := /bin/bash

## VARS AND ENVS
REPO_DIR ?= $(shell pwd | xargs echo -n)
GITTAG ?= $(shell git describe --tags --always --dirty)
SEMVER ?= $(shell head -n 1 sem-version)

DOCKER_PG_VOL := docker/pg_vol
DOCKER_PG_CONTAINER := docker_pg_container
DOCKER_HUB_REPO := kyledinh

## Sync vars with backend/src/main/scala/blogapp/QuillContext.scala
POSTGRES_DB := blogapp
POSTGRES_USER := postgres
POSTGRES_PASSWORD := password

## MAIN ##############################
.PHONY: backend-check check codegen codegn-clear docker-build fmt frontend-build postgres setup

backend-check:
	@echo; ./scripts/curl/backend-api-endpoint-check.sh	

backend-compile:
	@sbtn backend/clean
	@sbtn backend/compile 

backend-down:
	@sbtn backend/reStop

backend-up:
	@sbtn backend/reStart

check: 
	@echo "SEMVER: $(SEMVER)"
	@echo "GITTAG: $(GITTAG)"
	@echo "REPO_DIR: $(REPO_DIR)"
	@echo "DOCKER_PG_VOL: $(DOCKER_PG_VOL)"
	@echo "$(REPO_DIR)/$(DOCKER_PG_VOL)"
	@echo; ./scripts/check-installed.sh	

codegen:
	## Requires btk-cli (https://github.com/kyledinh/btk-go)
	btk -gen=model -i=specs/blogapp.0.0.3.yaml -d=output
	cat output/gen.model.pet.scala

codegen-clear:
	rm -rf output/*.*

## docker:publishLocal is still a blackbox, but will produce a working Docker image
docker-build: frontend-compile
	cd docker/ && ./build-blogapp-nginx-frontend.sh	

	sbt docker:publishLocal 
	docker tag blogapp-backend:$(SEMVER) $(DOCKER_HUB_REPO)/blogapp-backend:$(SEMVER)-$(GITTAG)
	docker images | grep blogapp 

## customize DOCKER_HUB_REPO to your repository
docker-push:
	docker push $(DOCKER_HUB_REPO)/blogapp-backend:$(SEMVER)-$(GITTAG)
	docker push $(DOCKER_HUB_REPO)/blogapp-nginx:$(SEMVER)-$(GITTAG)

docker-up:
	@echo "Running the backend as a Docker container, will connect to database through `host.docker.internal`"	
	docker run -p 4000:4000 -e DATABASE_URL=postgres://$(POSTGRES_USER):$(POSTGRES_PASSWORD)@host.docker.internal:5432/$(POSTGRES_DB) blogapp-backend:$(SEMVER)

docker-front-up:
	@echo "Running the frontend as a Docker container, will connect to database through `host.docker.internal`"	
	docker run -p 80:80 kyledinh/blogapp-nginx:latest

fmt:
	@scalafmt

frontend-compile:
	@sbtn frontend/fastLinkJS
	@cp frontend/target/scala-3.2.2/blogapp-frontend-fastopt/main.js html/.
	@echo "let BLOGAPP_SEMVER = '$(SEMVER)-$(GITTAG)';" > html/blogapp.js
	@echo "$(SEMVER)-$(GITTAG)" > html/sem-version 

frontend-up:
	@open http://localhost:3000
	@cd html && yarn exec vite

postgres-check:
	@docker exec -i docker_pg_container psql  -U $(POSTGRES_USER) -d $(POSTGRES_DB) -c "\l"
	@docker exec -i docker_pg_container psql  -U $(POSTGRES_USER) -d $(POSTGRES_DB) -c "\d"
	@docker exec -i docker_pg_container psql  -U $(POSTGRES_USER) -d $(POSTGRES_DB) -c "select * from person"
	@docker exec -i docker_pg_container psql  -U $(POSTGRES_USER) -d $(POSTGRES_DB) -c "select * from scrawl"

postgres-down:
	@docker stop $(DOCKER_PG_CONTAINER) 
	@docker rm $(DOCKER_PG_CONTAINER)

postgres-init-migrate:
	@cat ./backend/src/main/resources/db/migration/V1__create_blogapp.sql | docker exec -i docker_pg_container psql -U $(POSTGRES_USER) -d $(POSTGRES_DB)  
	@cat ./backend/src/main/resources/db/migration/V2__add_fixtures.sql | docker exec -i docker_pg_container psql -U $(POSTGRES_USER) -d $(POSTGRES_DB)  

postgres-reset:
	@$(MAKE) postgres-down
	@rm -rf $(DOCKER_PG_VOL)
	@mkdir $(DOCKER_PG_VOL)
	@$(MAKE) postgres-up
	@echo ".. wait for db to restart then (~10 secs), run: make postgres-init-migrate"

postgres-shell:
	@docker exec -it docker_pg_container psql postgres postgres

postgres-up:
	@docker run --name $(DOCKER_PG_CONTAINER) \
	-p 5432:5432 \
	-e POSTGRES_DB=$(POSTGRES_DB) \
	-e POSTGRES_USER=$(POSTGRES_USER) \
	-e POSTGRES_PASSWORD=$(POSTGRES_PASSWORD) \
	-v $(REPO_DIR)/$(DOCKER_PG_VOL):/var/lib/postgresql/data \
	-d postgres:14

setup:
	@echo "SETTING UP DOCKER FILES/DIR"
	$(shell [ -d $(DOCKER_PG_VOL) ] || mkdir $(DOCKER_PG_VOL))
	@cp kubernetes/desktop/.sample-deployment-blogapp-api.yaml kubernetes/desktop/deployment-blogapp-api.yaml
	@cp kubernetes/desktop/.sample-deployment-blogapp-database.yaml kubernetes/desktop/deployment-blogapp-database.yaml
	@cp kubernetes/desktop/.sample-deployment-blogapp-web.yaml kubernetes/desktop/deployment-blogapp-web.yaml
	@echo "yarn install for frontend"
	@cd html && yarn install

status:
	@echo "Docker/Database Status:"
	docker ps 
	@echo
	@echo "Backend Status:"
	sbtn backend/reStatus
	@echo
	@echo "Frontend/JS Vite Status:"
	ps -aef | grep vite

test-backend:
	@sbt test

test-compile:
	@sbt test:compile
