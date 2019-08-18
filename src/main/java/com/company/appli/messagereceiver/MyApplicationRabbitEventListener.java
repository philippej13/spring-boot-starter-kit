package com.company.appli.messagereceiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.ListenerContainerConsumerFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyApplicationRabbitEventListener implements ApplicationListener<ListenerContainerConsumerFailedEvent> {

    @Override
    public void onApplicationEvent(ListenerContainerConsumerFailedEvent listenerContainerConsumerFailedEvent) {
        log.error(listenerContainerConsumerFailedEvent.getReason());
    }
}
