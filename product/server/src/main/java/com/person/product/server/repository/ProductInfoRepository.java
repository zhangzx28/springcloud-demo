package com.person.product.server.repository;

import com.person.product.server.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findProductInfosByProductStatus(Integer productStatus);

    List<ProductInfo> findProductInfosByProductIdIn(List<String> productIdList);
}
