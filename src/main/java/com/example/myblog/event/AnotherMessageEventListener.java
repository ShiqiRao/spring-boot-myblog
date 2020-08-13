package com.example.myblog.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnotherMessageEventListener {

    @EventListener
    public void onApplicationEvent(MessageEvent messageEvent) {
        //处理接收到事件通知后的业务逻辑
        log.info("Other business……Message:" + messageEvent.getMessage());
    }
}

