package com.xyz.designpatterns.structure.decorator;

/**
 * Created by hzhsg on 2018/5/4.
 */
public class MochaCoffee implements Coffee{

    @Override
    public String description() {
        return "摩卡咖啡";
    }

    @Override
    public int price() {
        return 18;
    }

    @Override
    public void show() {
        System.out.println("描述:"+description()+"-价格"+price());
    }
}
