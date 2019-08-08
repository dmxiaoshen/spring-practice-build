package com.xyz.service.order.service;

import com.xyz.service.order.dao.OrderMapper;
import com.xyz.service.order.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Transactional
    public Integer create(String userId, String commodityCode, Integer count) {

        Integer orderMoney = count * 5;

        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);

        orderMapper.insertSelective(order);

        return orderMoney;

        //accountFeignClient.debit(userId, orderMoney);
    }

}
