package com.person.product.server.exception;

import com.person.product.server.enums.ResultEnum;

/**
 * @program: product
 * @description:
 * @author: zhangzx
 * @create: 2019-09-06 14:45
 */
public class ProductException extends RuntimeException{

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}