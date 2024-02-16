# First build
To run the app, you first need to build the spring docker using one of the following
 - Makefile: make build-spring
 - dockerfile: 
	mvn clean package
	docker build -t gachi-api/spring:latest .

# Build the docker-compose
You then need to run the docker-compose with either:
 - Makefile: make build
 - bash: docker-compose build

# Run the app
To run app, you can use:
 - Makefile: make up
or run the following commands:
	docker-compose stop
	docker-compose up -d --remove-orphans db
	docker-compose up -d --remove-orphans app-server


# Usefull commands
 - make logs-spring: output the logs of spring app
 - make logs-db: output the logs of mongodb server
 - make reload-spring: update the spring container 