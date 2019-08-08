package com.xyz.service.order.entity;

import lombok.Data;

@Data
public class Order {
    private Integer id;

    private String userId;

    private String commodityCode;

    private Integer count;

    private Integer money;
}