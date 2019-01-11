package com.company.appli.messagereceiver;

import org.springframework.amqp.rabbit.listener.ListenerContainerConsumerFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRabbitEventListener implements ApplicationListener<ListenerContainerConsumerFailedEvent> {

    @Override
    public void onApplicationEvent(ListenerContainerConsumerFailedEvent listenerContainerConsumerFailedEvent) {
        System.out.println(listenerContainerConsumerFailedEvent.getReason());
    }
}
