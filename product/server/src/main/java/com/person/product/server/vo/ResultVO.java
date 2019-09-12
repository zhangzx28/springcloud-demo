package com.person.product.server.vo;

import lombok.Data;

/**
 * @program: product
 * @description:
 * @author: zhangzx
 * @create: 2019-08-30 15:45
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}