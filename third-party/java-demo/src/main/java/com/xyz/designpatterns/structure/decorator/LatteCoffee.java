package com.xyz.designpatterns.structure.decorator;

/**
 * Created by hzhsg on 2018/5/4.
 */
public class LatteCoffee implements Coffee{

    @Override
    public String description() {
        return "拿铁咖啡";
    }

    @Override
    public int price() {
        return 16;
    }

    @Override
    public void show() {
        System.out.println("描述:"+description()+"-价格"+price());
    }
}
