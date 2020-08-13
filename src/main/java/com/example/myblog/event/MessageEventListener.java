package com.example.myblog.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageEventListener implements ApplicationListener<MessageEvent> {
    @Override
    public void onApplicationEvent(MessageEvent messageEvent) {
        //处理接收到事件通知后的业务逻辑
        log.info("Some business……Message:" + messageEvent.getMessage());
    }
}
