package com.company.appli.messagereceiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyRabbitErrorHandler implements RabbitListenerErrorHandler {

    @Override
    public Object handleError(Message message, org.springframework.messaging.Message<?> message1, ListenerExecutionFailedException e) throws Exception {
        log.error("Erreur RabbitMQ", e);
        return new ListenerExecutionFailedException("Erreur RabbitMQ", e, message);
        //Si listener.simple.default-requeue-rejected: true
        // utiliser throw new AmqpRejectAndDontRequeueException(e) pour éviter que le messsage tourne en boucle
    }
}