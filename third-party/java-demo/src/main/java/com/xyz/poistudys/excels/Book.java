package com.xyz.poistudys.excels;

import java.math.BigDecimal;

/**
 * Created by hzhsg on 2018/9/6.
 */
public class Book {

    private String name;

    private BigDecimal price;

    public Book(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
