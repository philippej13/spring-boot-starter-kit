package com.apriltechnologies.amadmin.messagereceiver;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @RabbitListener(queues= {"queueTest"}, errorHandler = "myRabbitErrorHandler")
    public void recievedMessage(Message msg) {

        System.out.println("Recieved Message: " + new String(msg.getBody()));
    }

}

