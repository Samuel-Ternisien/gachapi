# Gachapi: Application de summons

Pour lancer l'application, un docker-compose up s'occupe du build des différents apps, ainsi que leur lancement

## Un swagger est disponible pour chaque application:
- Application d'authentification: http://localhost:8080/swagger-ui/index.html
- Application joueurs: http://localhost:8081/swagger-ui/index.html#/
- Application monsters: http://localhost:8082/swagger-ui/index.html#/
- Application de Summoning: http://localhost:8083/swagger-ui/index.html#/


## Utilisation de l'application:
1. Lancer l'application via docker-compose up
2. Création d'un compte via l'application authentification (endpoint: /account), voir Swagger pour format
3. Une fois le compte disponible, il faut se logger via l'endpoint /account/login 
4. Il faut ensuite créer un joueur via l'api joueurs
5. Pour faire des invocations, il faut utiliser l'application de summoning avec l'endpoint /summons/{playerId} . Il faut passer en header le token de l'api authentification, et en variable d'url l'id de joueur
5. Pour consulter les monstres d'un joueur, il faut utilise l'endpoint /player/{playerId} de l'api monsters


Projet réalisé par:
- Thomas VASSEUR (Application combat)
- Salma EL MOUDEN (Application combat)
- Pierre VANDENBERGHE (Application player)
- Robyn FRANCOIS (Application monster)
- Samuel TERNISIEN (Application auth, summons, gestion des dockers)