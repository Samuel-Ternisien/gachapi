## Build apps (db and spring)
build:
	docker-compose build

## Start app
up:
	docker-compose up -d --remove-orphans

## Stop app
down:
	docker-compose down

## Start & build account
start-account:
	docker-compose build account-app
	docker-compose up -d --remove-orphans --force-recreate account-app

## Start & build player
start-account:
	docker-compose build player-app
	docker-compose up -d --remove-orphans --force-recreate player-app