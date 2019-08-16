package com.xyz.designpatterns.creational.builder;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class PhoneDirector {

    public Phone constructPhone(PhoneBuilder phoneBuilder){
        phoneBuilder.buildOs();
        phoneBuilder.buildPrice();
        phoneBuilder.buildModel();
        return phoneBuilder.build();
    }
}
