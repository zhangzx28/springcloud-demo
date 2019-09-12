package com.person.order.server.vo;

import lombok.Data;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-05 16:16
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String message;

    private T data;
}