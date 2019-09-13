package com.person.order.server.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-12 19:13
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.INPUT2)
    public String processInput(Object message) {
        log.info("StreamReceiver.processInput: {}", message);
        return "received";
    }

    @StreamListener(StreamClient.INPUT2)
    public void processInput2(String message) {
        log.info("processInput2: {}", message);
    }

    @StreamListener(StreamClient.OUTPUT)
    public void processOutput(Object message) {
        log.info("StreamReceiver.processOutput: {}", message);
    }

}