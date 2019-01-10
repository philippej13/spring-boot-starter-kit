# springboot-hello

* Springboot
* Swagger (springdoc)
* Rest
* RabbitMQ
* Spring security with Oauth

```
docker run -d --hostname my-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
docker exec -it 598fec303482 rabbitmqadmin publish routing_key=queueTest payload="test"
```