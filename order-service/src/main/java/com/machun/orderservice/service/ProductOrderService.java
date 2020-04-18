package com.machun.orderservice.service;

import com.machun.orderservice.entity.ProductOrder;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/13 13:34
 * @UpdateDate: 2020/4/13 13:34
 * @menu
 */
public interface ProductOrderService {

     /**
      * 下单方法一
      *
      * @author: machun
      * @param userId
      * @param productId
      * @return ProductOrder
      * @createDate: 2020/4/17 13:20
      * @version: V1.0
      * @status: undone
     */
    ProductOrder save(Long userId, Long productId);

     /**
      * 下单方法二
       *
      * @author: machun
      * @param userId
      * @param productId
      * @return ProductOrder
      * @createDate: 2020/4/17 13:22
      * @version: V1.0
      * @status: undone
     */
    ProductOrder saveByFeign(Long userId, Long productId);
}
