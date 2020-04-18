package com.machun.orderservice.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.machun.orderservice.entity.ProductOrder;
import com.machun.orderservice.service.ProductOrderService;
import com.machun.orderservice.service.apiclient.ProductClient;
import com.machun.orderservice.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/13 13:35
 * @UpdateDate: 2020/4/13 13:35
 * @menu
 */
@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
   private ProductClient productClient;

    /**
     * 下单方法一
     *
     * @param userId
     * @param productId
     * @return ProductOrder
     * @author: machun
     * @createDate: 2020/4/17 13:20
     * @version: V1.0
     * @status: undone
     */
    @Override
    public ProductOrder save(Long userId, Long productId) {
        ServiceInstance instance = this.loadBalancerClient.choose("product-service");
        String getProductUrl = String.format("http://%s:%s/product/list/%s", instance.getHost(), instance.getPort(), productId);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> productMap = (Map)restTemplate.getForObject(getProductUrl, Map.class, new Object[0]);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrderId(12121L);
        productOrder.setUserId(userId);
        productOrder.setUserName("user:" + userId);
        productOrder.setOrderNo((new Date()).toString());
        productOrder.setProductId(productId);
        productOrder.setProductName(productMap.get("productName").toString() + instance.getPort());
        productOrder.setProductPrice((Double)productMap.get("price"));
        return productOrder;
    }

    /**
     * 下单方法二
     *
     * @param userId
     * @param productId
     * @return ProductOrder
     * @author: machun
     * @createDate: 2020/4/17 13:22
     * @version: V1.0
     * @status: undone
     */
    @Override
    public ProductOrder saveByFeign(Long userId, Long productId) {

        String product = this.productClient.getProduct(productId);
        JsonNode productJson = JsonUtil.str2JsonNode(product);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrderId(12121L);
        productOrder.setUserId(userId);
        productOrder.setUserName("user:" + userId);
        productOrder.setOrderNo((new Date()).toString());
        productOrder.setProductId(productId);
        productOrder.setProductName(productJson.get("productName").toString());
        productOrder.setProductPrice(Double.parseDouble(productJson.get("price").toString()));
        productOrder.setAddress(productJson.get("productAddress").toString());
        return productOrder;
    }
}
