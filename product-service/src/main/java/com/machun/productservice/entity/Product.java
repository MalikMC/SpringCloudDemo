package com.machun.productservice.entity;

import java.io.Serializable;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/13 10:34
 * @UpdateDate: 2020/4/13 10:34
 * @menu
 */

public class Product implements Serializable {
    private long id;
    private String productName;
    private double price;
    private int store;
    private String productAddress;

    public Product() {
    }

    public Product(long id, String productName, double price, int store, String productAddress) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.store = store;
        this.productAddress = productAddress;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStore() {
        return this.store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public String getProductAddress() {
        return this.productAddress;
    }

    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress;
    }

    @Override
    public String toString() {
        return "Product{id=" + this.id + ", productName='" + this.productName + '\'' + ", price=" + this.price + ", store=" + this.store + ", productAddress='" + this.productAddress + '\'' + '}';
    }
}
