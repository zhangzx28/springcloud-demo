package com.person.order.server.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

    String INPUT = "input";
    String INPUT2 = "input2";
    String OUTPUT = "output";
    /**
     *  @Input注解区分了一个输入channel，通过它接收消息到应用中
     */
    @Input(INPUT)
    SubscribableChannel input();

    @Input(INPUT2)
    SubscribableChannel input2();

    /**
     * @Output注解 区分输出channel，消息通过它离开应用
     */
    @Output(OUTPUT)
    MessageChannel output();

}
