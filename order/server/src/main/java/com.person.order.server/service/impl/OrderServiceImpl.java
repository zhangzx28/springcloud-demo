package com.person.order.server.service.impl;

import com.person.order.server.dataobject.OrderDetail;
import com.person.order.server.dataobject.OrderMaster;
import com.person.order.server.dto.OrderDTO;
import com.person.order.server.enums.OrderStatusEnum;
import com.person.order.server.enums.PayStatusEnum;
import com.person.order.server.enums.ResultEnum;
import com.person.order.server.exception.OrderException;
import com.person.order.server.repository.OrderDetailRepository;
import com.person.order.server.repository.OrderMasterRepository;
import com.person.order.server.service.OrderService;
import com.person.order.server.utils.KeyUtil;
import com.person.product.client.ProductClient;
import com.person.product.common.DecreaseStockInput;
import com.person.product.common.ProductInfoOutput;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: order
 * @description:
 * @author: zhangzx
 * @create: 2019-09-05 09:18
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @GlobalTransactional
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();

        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        List<String> productIdList = orderDetailList.stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        //查询商品信息
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        //计算总价(生成订单详情)
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDetailList) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //扣库存
        List<DecreaseStockInput> decreaseStockInputList = orderDetailList.stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreseStock(decreaseStockInputList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        //1. 查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2. 判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (!OrderStatusEnum.NEW.getCode().equals(orderMaster.getOrderStatus())) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //3. 修改订单状态为完结
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);

        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findOrderDetailsByOrderId(orderMaster.getOrderId());
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}