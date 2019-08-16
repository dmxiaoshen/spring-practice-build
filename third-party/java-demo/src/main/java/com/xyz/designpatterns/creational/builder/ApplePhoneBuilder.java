package com.xyz.designpatterns.creational.builder;

import java.math.BigDecimal;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class ApplePhoneBuilder implements PhoneBuilder{

    private Phone phone = new Phone();
    @Override
    public void buildOs() {
        phone.setOs("ios-9");
    }

    @Override
    public void buildPrice() {
        phone.setPrice(new BigDecimal("5999"));
    }

    @Override
    public void buildModel() {
        phone.setModel("iphone7");
    }

    @Override
    public Phone build() {
        return phone;
    }
}
