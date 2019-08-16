package com.xyz.designpatterns.structure.decorator;

/**
 * Created by hzhsg on 2018/5/4.
 */
public class SugarCoffeeDecorator implements CoffeeDecorator{

    private Coffee coffee;

    public SugarCoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String description() {
        return "加了糖,"+coffee.description();
    }

    @Override
    public int price() {
        return 1+coffee.price();
    }

    @Override
    public void show() {
        System.out.println("描述:"+description()+"-价格"+price());
    }
}
