package com.person.product.server.service.impl;

import com.person.product.server.dataobject.ProductCategory;
import com.person.product.server.repository.ProductCategoryRepository;
import com.person.product.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: product
 * @description:
 * @author: zhangzx
 * @create: 2019-08-30 15:38
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findProductCategoriesByCategoryTypeIn(categoryTypeList);
    }
}