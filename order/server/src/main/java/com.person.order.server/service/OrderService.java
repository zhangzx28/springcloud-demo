package com.person.order.server.service;

import com.person.order.server.dto.OrderDTO;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-05 09:13
 */
public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);
}