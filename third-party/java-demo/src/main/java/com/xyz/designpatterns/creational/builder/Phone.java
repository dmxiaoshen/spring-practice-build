package com.xyz.designpatterns.creational.builder;

import java.math.BigDecimal;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class Phone {

    private String os;
    private BigDecimal price;
    private String model;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void show(){
        System.out.println("Phone{" +
                "os='" + os + '\'' +
                ", price=" + price +
                ", model='" + model + '\'' +
                '}');
    }
}
