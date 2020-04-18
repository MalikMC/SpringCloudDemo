package com.machun.orderservice.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.machun.orderservice.service.ProductOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/13 13:33
 * @UpdateDate: 2020/4/13 13:33
 * @menu
 */

@RequestMapping({"order"})
@RestController
public class ProductOrderController {
    @Autowired
    ProductOrderService productOrderService;
    @Autowired
    StringRedisTemplate redisTemplate;

    public ProductOrderController() {
    }

    @RequestMapping({"save/{userId}/{productId}"})
    @HystrixCommand(
            fallbackMethod = "saveOrderFail"
    )
    public Object saveOrder(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        Map<String, Object> msg = new HashMap(16);
        msg.put("code:", 1);
        msg.put("msg:", "OK");
        msg.put("data:", this.productOrderService.saveByFeign(userId, productId).toString());
        return msg;
    }

    private Object saveOrderFail(Long userId, Long productId) {
        String saveOrderFailKey = "Save_Order_Fail";
        ThreadFactory namedThreadFactory = (new ThreadFactoryBuilder()).setNameFormat("Thread-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        singleThreadPool.execute(() -> {
            this.sendFailMessage(userId, productId, saveOrderFailKey);
        });
        singleThreadPool.shutdown();
        Map<String, Object> msg = new HashMap(16);
        msg.put("code:", -1);
        msg.put("msg:", "下单人数过多，请稍后再试！");
        return msg;
    }

    private void sendFailMessage(Long userId, Long productId, String saveOrderFailKey) {
        String keyValue = (String)this.redisTemplate.opsForValue().get(saveOrderFailKey);
        if (StringUtils.hasLength(keyValue)) {
            System.out.println(String.format("Save Order 异常己发出，%s 分钟内不再重复发送！", 1));
        } else {
            System.out.println("己调用方法发出短信通知或邮件，Save Order Fail");
            this.redisTemplate.opsForValue().set(saveOrderFailKey, userId + ":" + productId, 1L, TimeUnit.MINUTES);
        }

    }
}
