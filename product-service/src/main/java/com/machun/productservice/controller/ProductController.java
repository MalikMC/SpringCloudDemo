package com.machun.productservice.controller;

import com.machun.productservice.entity.Product;
import com.machun.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/13 10:33
 * @UpdateDate: 2020/4/13 10:33
 * @menu
 */

@RequestMapping({"product"})
@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Value("${server.port}")
    private String port;

    public ProductController() {
    }

    @RequestMapping({"list"})
    @ResponseBody
    public List<Product> getProduct() {
        return this.productService.findAllProduct();
    }

    @GetMapping({"list/{id}"})
    @ResponseBody
    public Product getProductById(@PathVariable("id") Long id) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2L);
        Product product = this.productService.findByProductId(id);
        product.setProductAddress(this.port);
        return product;
    }
}