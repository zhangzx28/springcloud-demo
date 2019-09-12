package com.person.order.server.repository;

import com.person.order.server.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

//    List<OrderDetail> findByOrderId(String orderId);
}
