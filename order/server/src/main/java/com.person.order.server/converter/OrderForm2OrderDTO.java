package com.person.order.server.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.person.order.server.dataobject.OrderDetail;
import com.person.order.server.dto.OrderDTO;
import com.person.order.server.enums.ResultEnum;
import com.person.order.server.exception.OrderException;
import com.person.order.server.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-05 14:58
 */
@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList;

        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【json转换】错误, string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}