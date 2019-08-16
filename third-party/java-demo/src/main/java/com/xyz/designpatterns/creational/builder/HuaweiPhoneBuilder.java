package com.xyz.designpatterns.creational.builder;

import java.math.BigDecimal;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class HuaweiPhoneBuilder implements PhoneBuilder{

    private Phone phone = new Phone();
    @Override
    public void buildOs() {
        phone.setOs("android-6");
    }

    @Override
    public void buildPrice() {
        phone.setPrice(new BigDecimal("48888"));
    }

    @Override
    public void buildModel() {
        phone.setModel("meta9");
    }

    @Override
    public Phone build() {
        return phone;
    }
}
