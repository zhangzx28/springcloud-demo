package com.person.order.server.dto;

import com.person.order.server.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-05 09:16
 */
@Data
public class OrderDTO {
    /** 订单id. */
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus;

    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}