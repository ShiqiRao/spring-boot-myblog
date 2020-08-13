package com.example.myblog.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishAnEvent(String message) {
        log.info("Publishing an event.Message:" + message);
        applicationEventPublisher.publishEvent(new MessageEvent(this, message));
    }

}
