package com.person.order.server.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-05 10:10
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}