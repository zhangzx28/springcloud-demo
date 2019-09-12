package com.person.product.server.repository;

import com.person.product.server.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findProductCategoriesByCategoryTypeIn() {
        List<ProductCategory> list = productCategoryRepository
                .findProductCategoriesByCategoryTypeIn(Arrays
                        .asList(new Integer[] {11, 22}));
        Assert.assertTrue(list.size() > 0);
    }
}