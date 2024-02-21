## Create new jar of the spring app and build a new image for docker
build-spring:
	mvn clean package
	docker build -t gachi-api/spring:latest .

## Build apps (db and spring)
build:
	docker-compose build

## Start app
up:
	docker-compose up -d --remove-orphans db
	timeout 5
	docker-compose up -d --remove-orphans app-server

## Stop app
down:
	docker-compose down

## Refresh spring
refresh-spring:
	docker cp ./target/gachapi-0.0.1-SNAPSHOT.jar gachapi-app-server-1
	docker-compose down app-server
	docker-compose up -d --remove-orphans app-server


## Log db
logs-db:
	docker-compose logs -f db

## Log spring-app
logs-spring:
	docker-compose logs -f app-server

## Login db
login-db:
	docker exec -it gachapi-db-1 /bin/mongosh gachadb
