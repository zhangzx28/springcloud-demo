package com.person.product.server.repository;


import com.person.product.server.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {

    List<ProductCategory> findProductCategoriesByCategoryTypeIn(List<Integer> categoryTypeList);
}
