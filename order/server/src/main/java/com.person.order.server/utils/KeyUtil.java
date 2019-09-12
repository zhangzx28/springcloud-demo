package com.person.order.server.utils;

import java.util.Random;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-05 09:37
 */
public class KeyUtil {

    public static synchronized String getUniqueKey() {

        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}