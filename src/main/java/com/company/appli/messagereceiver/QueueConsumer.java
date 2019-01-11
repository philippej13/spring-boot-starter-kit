package com.company.appli.messagereceiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @RabbitListener(queues= {"queueTest"}, errorHandler = "myRabbitErrorHandler")
    public void recievedMessage(Message msg) {

        System.out.println("Recieved Message: " + new String(msg.getBody()));
    }

}

