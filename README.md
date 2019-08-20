# spring-boot-starter-kit

* Springboot
* Swagger (springfox)
* Rest
    - Intercepteur
    - ResponseErrorHandler
* RabbitMQ
* Spring security with Oauth
* MongoDB
* Elasticsearch  
* Junit 5

TODO

* Script de création de file
* Logger différent pour les entrées/sortie (HTTP)
* Mock
* JUnit avec Init DB
* Docker compose
* Cache
--------------------------------------------

Run Rabbit + Create queue + Publish Message

```
docker run -d --hostname my-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
docker exec -it 69731352de2b rabbitmqadmin declare queue --vhost=/ name=queueTest durable=true
docker exec -it 69731352de2b rabbitmqadmin publish routing_key=queueTest payload="test"
```
Login IHM Rabit guest/guest

--------------------------------------
Lancement du serveur MongoDB 
```
docker run -d -p 27017:27017 -v /home/philippe/Documents/mongodb/data4:/data/db mongo:4.0.9
```
Pour exécuter le client Mongo depuis le container MongoDB pour créer la base starterkit
```
docker exec -it f0f6c1885507 mongo
use starterkit
```
Et créer le user
```
mongo localhost:27017/starterkit /home/philippe/GIT/spring-boot-starter-kit/src/main/config/initMongoDB.js
```

-------------------------
Lancement d'un elasticsearch
```
docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.3.0
```


Swagger URL : [http://localhost:8080/swagger-ui.html]

Exemples Requêtes REST :

```
GET http://localhost:8080/api/security/admin/accounts/domainesqdf/aee7a221-08b4-4ede-8c0e-9a43c3d3cf1c
```

```
GET http://localhost:8080/api/security/admin/accounts/domainesqdf
```

```
POST http://localhost:8080/api/security/admin/accounts
{
	"email": "sdfsdf@email.com",
	"domaine": "domainesqdf",
	"name": "le nom",
	"firstName":"le prenomqsdf"
}
```

```
mvn spring-boot:run
```
