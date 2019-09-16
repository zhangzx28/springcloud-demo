package com.person.order.server.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.person.order.server.utils.JsonUtil;
import com.person.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-13 11:36
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        try {
            List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>) JsonUtil.fromJson(message,
                    new TypeReference<List<ProductInfoOutput>>() {
                    });
            log.info("从队列【{}】接收到消息：{}", "productInfo", productInfoOutputList);

            // 存储到redis中
            for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
                stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()),
                        String.valueOf(productInfoOutput.getProductStock()));
            }
        } catch (Exception e) {
            log.error("异常消息【{}】", message);
        }
    }
}