package com.person.order.server.controller;

import com.person.order.server.dto.OrderDTO;
import com.person.order.server.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-12 19:17
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("111");
        orderDTO.setBuyerName("测试");
        orderDTO.setBuyerAddress("china");
        streamClient.input().send(MessageBuilder.withPayload(orderDTO).build());
    }
}