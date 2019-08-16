package com.xyz.designpatterns.creational.factory;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class RectangleButton implements Button{
    @Override
    public void press() {
        System.out.println("Rectangle Button Press");
    }
}
