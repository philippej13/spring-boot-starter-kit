package com.company.appli.messagereceiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

@Component
public class MyRabbitErrorHandler implements RabbitListenerErrorHandler {

    @Override
    public Object handleError(Message message, org.springframework.messaging.Message<?> message1, ListenerExecutionFailedException e) throws Exception {
        System.out.println("----------------------------------");
        System.out.println(e);
        return new ListenerExecutionFailedException("Mon erreur", e, message);
    }
}