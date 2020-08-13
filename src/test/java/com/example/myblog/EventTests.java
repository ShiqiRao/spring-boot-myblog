package com.example.myblog;

import com.example.myblog.event.MessageEventPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {BlogApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventTests {

    @Autowired
    MessageEventPublisher publisher;

    @Test
    public void publishAnEvent_thenCheckConsole() {
        publisher.publishAnEvent("Bada bing,bada boom……");
    }

}
