package com.machun.orderservice.service.apiclient.failback;

import com.machun.orderservice.service.apiclient.ProductClient;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/13 13:38
 * @UpdateDate: 2020/4/13 13:38
 * @menu
 */
@Component
public class ProductClientFailBack implements ProductClient {
    public ProductClientFailBack() {
    }

    @Override
    public String getProduct(Long productId) {
        System.out.println("----------------ERROR-------------get Product Fail!--------------ERROR----------------");
        return null;
    }
}
