package com.person.product.server.controller;

import com.person.product.common.DecreaseStockInput;
import com.person.product.common.ProductInfoOutput;
import com.person.product.server.dataobject.ProductCategory;
import com.person.product.server.dataobject.ProductInfo;
import com.person.product.server.dto.CartDTO;
import com.person.product.server.service.CategoryService;
import com.person.product.server.service.ProductService;
import com.person.product.server.utils.ResultVOUtil;
import com.person.product.server.vo.ProductInfoVO;
import com.person.product.server.vo.ProductVO;
import com.person.product.server.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: product
 * @description: 商品
 * @author: zhangzx
 * @create: 2019-08-29 16:32
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /***
     * @Description:
     * @Param: []
     * @return: void
     * @Author: zhangzx
     * @Date: 2019-08-29
     */
    @GetMapping("/list")
    public ResultVO<List<ProductVO>> list() {

        //查询所有在架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //获取类目列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .distinct()
                .collect(Collectors.toList());
        //从数据库查询类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        productCategoryList.forEach(c -> {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(c, productVO);
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            productInfoList.forEach(p -> {
                if (p.getCategoryType().equals(c.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(p, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            });
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        });

        return ResultVOUtil.success(productVOList);
    }

    /*** 
     * @Description: 提供给订单服务用的产品列表
     * @Param: [productIdList]
     * @return: java.util.List<com.person.product.dataobject.ProductInfo>
     * @Author: zhangzx
     * @Date: 2019-09-06 
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
        return productService.findList(productIdList);
    }

    @PostMapping("/decreseStock")
    public void decreseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productService.decreseStock(decreaseStockInputList);
    }

}