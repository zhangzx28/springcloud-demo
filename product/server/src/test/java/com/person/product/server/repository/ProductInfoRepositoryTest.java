package com.person.product.server.repository;

import com.person.product.server.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findProductInfosByProductStatus() {
        List<ProductInfo> list = productInfoRepository.findProductInfosByProductStatus(0);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findProductInfosByProductIdIn() {
        List<ProductInfo> list = productInfoRepository.findProductInfosByProductIdIn(Arrays
                .asList(new String[] {"157875196366160022", "157875227953464068"}));
        assertTrue(list.size() > 0);
    }
}