package com.person.product.server.dto;

import lombok.Data;

/**
 * @program: product
 * @description:
 * @author: zhangzx
 * @create: 2019-09-06 14:33
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO() {

    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}