package com.person.apigateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

/**
 * @program: api-gateway
 * @description: 配置动态刷新
 * @author: zhangzx
 * @create: 2019-09-23 14:37
 */
//@Component
//public class ZuulConfig {
//
//    @ConfigurationProperties("zuul")
//    @RefreshScope
//    public ZuulProperties zuulProperties() {
//        return new ZuulProperties();
//    }
//}