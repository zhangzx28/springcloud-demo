package com.person.order.server.exception;

import com.person.order.server.enums.ResultEnum;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-05 14:51
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}