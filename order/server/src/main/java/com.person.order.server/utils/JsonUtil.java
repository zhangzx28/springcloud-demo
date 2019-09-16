package com.person.order.server.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @program: product
 * @description:
 * @author: zhangzx
 * @create: 2019-09-13 11:02
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object fromJson(String str, Class classType) {
        try {
            return objectMapper.readValue(str, classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object fromJson(String str, TypeReference typeReference) {
        try {
            return objectMapper.readValue(str, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
