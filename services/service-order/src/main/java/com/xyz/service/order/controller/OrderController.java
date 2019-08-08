package com.xyz.service.order.controller;

import com.xyz.service.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public Integer create(String userId, String commodityCode, Integer count) {
        return orderService.create(userId, commodityCode, count);
    }

}
