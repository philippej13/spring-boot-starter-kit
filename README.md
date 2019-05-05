# springboot-hello

* Springboot
* Swagger (springfox)
* Rest
* RabbitMQ
* Spring security with Oauth
* MongoDB 

TODO
* Elasticsearch 
* Script de création de file
--------------------------------------------

Lancement Rabbit

```
docker run -d --hostname my-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
docker exec -it 598fec303482 rabbitmqadmin publish routing_key=queueTest payload="test"
```
Login IHM Rabit guest/guest

Lancement MongoDB
```
docker run -d -p 27017:27017 -v /home/philippe/Documents/mongodb/data4:/data/db mongo:4.0.9
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
