package com.machun.orderservice.entity;

import java.io.Serializable;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/13 13:34
 * @UpdateDate: 2020/4/13 13:34
 * @menu
 */

public class ProductOrder implements Serializable {
    private Long orderId;
    private String orderNo;
    private Long userId;
    private String userName;
    private Long productId;
    private String productName;
    private double productPrice;
    private String address;

    public ProductOrder() {
    }

    public ProductOrder(Long orderId, String orderNo, Long userId, String userName, Long productId, String productName, double productPrice, String address) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.userId = userId;
        this.userName = userName;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.address = address;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ProductOrder{orderId=" + this.orderId + ", orderNo='" + this.orderNo + '\'' + ", userId=" + this.userId + ", userName='" + this.userName + '\'' + ", productId=" + this.productId + ", productName='" + this.productName + '\'' + ", productPrice='" + this.productPrice + '\'' + ", address='" + this.address + '\'' + '}';
    }
}
