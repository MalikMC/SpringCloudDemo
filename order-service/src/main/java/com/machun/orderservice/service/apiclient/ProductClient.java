package com.machun.orderservice.service.apiclient;

import com.machun.orderservice.service.apiclient.failback.ProductClientFailBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/13 13:36
 * @UpdateDate: 2020/4/13 13:36
 * @menu
 */
@FeignClient(
        name = "product-service",
        fallback = ProductClientFailBack.class
)
public interface ProductClient {

     /**
      * 调用产品服务获取产品信息
       *
      * @author: machun
      * @param productId
      * @return String
      * @createDate: 2020/4/17 13:29
      * @version: V1.0
      * @status: undone
     */
    @RequestMapping({"product/list/{productId}"})
    String getProduct(@PathVariable Long productId);
}
