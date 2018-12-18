package com.apriltechnologies.amadmin.messagereceiver;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues= {"queue3"})
    public void recievedMessage(Message msg) {
        System.out.println("Recieved Message: " + msg.getBody());
    }

}