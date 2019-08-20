package com.company.appli.messagereceiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(name = "rabbitmq.listener.active", havingValue = "true")
public class QueueConsumer {

    @RabbitListener(queues = {"queueTest"}, errorHandler = "myRabbitErrorHandler")
    public void recievedMessage(Message msg) {
        try {
            log.info("Recieved Message: " + new String(msg.getBody()));
        } catch (Exception e) {
            String errorMsg = "Erreur lors du traitement du message suivant : " + new String(msg.getBody());
            log.error(errorMsg, e);
            throw e;
        }
    }

}

