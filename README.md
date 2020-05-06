# spring-boot-starter-kit

* Springboot
* Swagger (springfox)
* Rest
    - Intercepteur
    - ResponseErrorHandler
* RabbitMQ
* Configuration du vhost et de la file JMS RabbitMq à l'initialisation du container
* Spring security with Oauth
* MongoDB
* Elasticsearch  
* Junit 5
* Cache
* Docker
* Docker compose
* Déploiement dans Kubernetes dans un cluster multipass
* Skaffold & Jib

TODO

* Logger différent pour les entrées/sortie (HTTP)
* Mock / WireMock
* JUnit avec Init DB
* Secret


--------------------------------------------

Run Rabbit + Create queue + Publish Message

```
docker run -d --hostname my-rabbit -e RABBITMQ_DEFAULT_VHOST=vhost1 -p 15672:15672 -p 5672:5672 rabbitmq:3-management
docker exec -it 69731352de2b rabbitmqadmin declare queue --vhost=/vhost1 name=queueTest durable=true
docker exec -it bbc0455e572d rabbitmqadmin publish routing_key=queueTest payload="test" --vhost=/vhost1
```
Login IHM RabbitMQ guest/guest

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
Index : http://es-server:9200/account/_search

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

## Rabbit MQ / Spring Boot : Handling Errors/Exception and configuration

https://docs.google.com/document/d/1I2cHdSN9Ch2C8E2qtFYjNwONR1gilVdaRgBCeK67FlE/edit?usp=sharing

Erreurs :
Si le message d'erreur suivant apparaît : "Bean method 'buildProperties' in 'ProjectInfoAutoConfiguration' not loaded because @ConditionalOnResource did not find resource '${spring.info.build.location:classpath:META-INF/build-info.properties}'"
Faire un mvn package


#Build image Docker
```
docker build -f docker/Dockerfile -t spring-boot-starter-kit:0.0.1 .
```
# Run in docker
Les fichiers de configuration de l'application et l'emplacements des log sont externalisés
Les volumes /app/logs et /app/config existent pour cela
La variable LOGGING_CONFIG doit être surchagée

```
docker run -p 8083:8083 -it -e LOGGING_CONFIG=/app/config/logback.xml -v /home/linux/Documents/GIT/spring-boot-starter-kit/src/main/resources:/app/config:ro -v  /tmp/logs:/app/logs spring-boot-starter-kit-build
```

#Run with docker-compose

```
cd docker/compose
docker-compose up
```


##Kubernetes
Deux exemples pour gérer la configuration 

#Directement avec les fichiers de configuratons (application.yml et logback.xml)
Dans l'exemple de déploiement (deploy-without-configMap), il s'agit d'utiliser un intiContainer pour faire un git afin de récupérer les fichiers et de les monter dans un volume du container.


#Création de du configMap avec les deux fichiers de configuration (application.yml et logback.xml)
``` 
kubectl create configmap spring-boot-starter-kit-config --from-file=src/main/resources/logback.xml --from-file=src/main/resources/application.yml
```

Et "montage" du configMap dans un volume 
```
          envFrom:   # <- Magic happens here
            - configMapRef:
                name: spring-boot-starter-kit-config
...
...
      volumes:
        - name: config-volume
          configMap:
            name: spring-boot-starter-kit-config 
```



#Run a local registry
docker volume create local_registry
docker container run -d --name registry.local -v local_registry:/var/lib/registry --restart always -p 5000:5000 registry:2

##Dans le cluster K3s
Edit file /etc/rancher/k3s/registries.yaml
```
mirrors:
  "192.168.0.10:5000":
    endpoint:
      - "http://192.168.0.10:5000"
```
Restart K3s 

```
sudo service k3s restart
```

##Sur la machine local (permet de faire un docker push)
Dans fichier /etc/docker/daemon.json
```json
{
  "insecure-registries" : ["myregistrydomain.com:5000"]
}
```
Puis restart du service docker
```
sudo service docker restart
```
#Skaffold
