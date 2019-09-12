package com.person.order.server.enums;

import lombok.Getter;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-08-31 15:35
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}