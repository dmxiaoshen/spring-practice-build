package com.xyz.designpatterns.behavior.command;

/**
 * Created by hzhsg on 2018/5/3.
 */
public class Television implements Equipment{
    @Override
    public void on() {
        System.out.println("电视开机");
    }

    @Override
    public void off() {
        System.out.println("电视关机");
    }
}
