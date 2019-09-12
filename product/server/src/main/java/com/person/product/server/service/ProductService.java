package com.person.product.server.service;

import com.person.product.common.DecreaseStockInput;
import com.person.product.common.ProductInfoOutput;
import com.person.product.server.dataobject.ProductInfo;
import com.person.product.server.dto.CartDTO;

import java.util.List;

/**
 * @program: product
 * @description:
 * @author: zhangzx
 * @create: 2019-08-30 15:18
 */
public interface ProductService {

    List<ProductInfo> findUpAll();

    List<ProductInfoOutput> findList(List<String> productIdList);

    void decreseStock(List<DecreaseStockInput> decreaseStockInputList);

}