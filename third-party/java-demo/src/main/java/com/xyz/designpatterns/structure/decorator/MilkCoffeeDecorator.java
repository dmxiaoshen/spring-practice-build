package com.xyz.designpatterns.structure.decorator;

/**
 * Created by hzhsg on 2018/5/4.
 */
public class MilkCoffeeDecorator implements CoffeeDecorator {

    private Coffee coffee;

    public MilkCoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String description() {
        return "加了牛奶,"+coffee.description();
    }

    @Override
    public int price() {
        return 2+coffee.price();
    }

    @Override
    public void show() {
        System.out.println("描述:"+description()+"-价格"+price());
    }
}
