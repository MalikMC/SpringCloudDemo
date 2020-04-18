package com.machun.productservice.service.impl;

import com.machun.productservice.entity.Product;
import com.machun.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/13 10:39
 * @UpdateDate: 2020/4/13 10:39
 * @menu
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Map<Long, Product> productMap = new HashMap();

    static {
        Product p1 = new Product(1L, "小米电视E45", 1873.0D, 1, (String) null);
        Product p2 = new Product(2L, "小米手机Red mi K30", 2816.0D, 3, (String) null);
        Product p3 = new Product(3L, "小米空调", 2143.0D, 1, (String) null);
        Product p4 = new Product(4L, "联想平板 M19", 1512.0D, 2, (String) null);
        Product p5 = new Product(5L, "Moto X", 2415.0D, 4, (String) null);
        productMap.put(p1.getId(), p1);
        productMap.put(p2.getId(), p2);
        productMap.put(p3.getId(), p3);
        productMap.put(p4.getId(), p4);
        productMap.put(p5.getId(), p5);
    }

    @Override
    public List<Product> findAllProduct() {
        return new ArrayList(productMap.values());
    }

    @Override
    public Product findByProductId(Long id) {
        return productMap.get(id);
    }
}
