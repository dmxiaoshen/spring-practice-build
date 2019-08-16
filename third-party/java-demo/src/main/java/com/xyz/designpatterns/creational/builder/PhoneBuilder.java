package com.xyz.designpatterns.creational.builder;

/**
 * Created by hzhsg on 2018/5/2.
 */
public interface PhoneBuilder {

    void buildOs();

    void buildPrice();

    void buildModel();

    Phone build();
}
