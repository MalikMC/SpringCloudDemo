package com.machun.productservice.service;

import com.machun.productservice.entity.Product;

import java.util.List;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/13 10:35
 * @UpdateDate: 2020/4/13 10:35
 * @menu 产品服务
 */
public interface ProductService {

    List<Product> findAllProduct();

    Product findByProductId(Long id);
}
