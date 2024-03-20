## Build all apps
build:
	docker-compose build

## Start all app
up:
	docker-compose up -d --remove-orphans

## Stop all app
down:
	docker-compose down

## Start & build account app
start-account:
	docker-compose build account-app
	docker-compose up -d --remove-orphans --force-recreate account-app

## Start & build player app
start-player:
	docker-compose build player-app
	docker-compose up -d --remove-orphans --force-recreate player-app

## Start & build monster app
start-monster:
	docker-compose build monster-app
	docker-compose up -d --remove-orphans --force-recreate monster-app

## Start db
start-db:
	docker-compose up -d db

## Stop account app
stop-account:
	docker-compose down account-app

## Stop player app
stop-player:
	docker-compose down player-app

## Stop monster app
stop-monster:
	docker-compose down monster-app

## Stop db
stop-db:
	docker-compose down db

## Stop all services except db (for debug usage)
stop-services:
	docker-compose down account-app monster-app player-app