package com.person.client.controller;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: client
 * @description:
 * @author: zhangzx
 * @create: 2019-08-28 11:17
 */
@Controller
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private EurekaClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        ApplicationInfoManager manager = client.getApplicationInfoManager();
        InstanceInfo info = manager.getInfo();
        logger.info("/hello, host:" + info.getHostName() + ", servie_id:" + info.getInstanceId());
        return "HelloWorld";
    }
}