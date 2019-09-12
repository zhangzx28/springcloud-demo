package com.person.product.server.service;

import com.person.product.common.DecreaseStockInput;
import com.person.product.common.ProductInfoOutput;
import com.person.product.server.ProductApplicationTests;
import com.person.product.server.dataobject.ProductInfo;
import com.person.product.server.dto.CartDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@Component
public class ProductServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findList() {
        List<ProductInfoOutput> list = productService.findList(Arrays.
                asList("157875196366160022", "157875227953464068"));
        assertTrue(list.size() > 0);
    }

    @Test
    public void decreseStock() {
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput("157875196366160022", 2);
        productService.decreseStock(Arrays.asList(decreaseStockInput));
    }
}