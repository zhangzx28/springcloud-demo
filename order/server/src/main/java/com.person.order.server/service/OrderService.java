package com.person.order.server.service;

import com.person.order.server.dto.OrderDTO;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-05 09:13
 */
public interface OrderService {
    
    /** 
     * @Description: 创建订单 
     * @Param: [orderDTO] 
     * @return: com.person.order.server.dto.OrderDTO 
     * @Author: zhangzx 
     * @Date: 2019-10-11 
     */
    OrderDTO create(OrderDTO orderDTO);
    
    /*** 
     * @Description: 完结订单(只能卖家操作)
     * @Param: [orderId] 
     * @return: com.person.order.server.dto.OrderDTO 
     * @Author: zhangzx 
     * @Date: 2019-10-11 
     */
    OrderDTO finish(String orderId);
}