package com.xyz.designpatterns.behavior.strategy;

/**
 * Created by hzhsg on 2018/5/9.
 */
public class FreeStrategy implements IStrategy{
    @Override
    public double doCalculate(double money) {
        System.out.println("免费大酬宾");
        return 0;
    }
}
