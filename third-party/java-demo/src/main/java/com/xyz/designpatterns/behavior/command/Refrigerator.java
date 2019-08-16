package com.xyz.designpatterns.behavior.command;

/**
 * Created by hzhsg on 2018/5/3.
 */
public class Refrigerator implements Equipment{
    @Override
    public void on() {
        System.out.println("冰箱开机");
    }

    @Override
    public void off() {
        System.out.println("冰箱关机");
    }
}
