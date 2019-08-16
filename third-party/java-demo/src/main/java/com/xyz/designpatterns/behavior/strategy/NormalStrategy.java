package com.xyz.designpatterns.behavior.strategy;

/**
 * Created by hzhsg on 2018/5/9.
 */
public class NormalStrategy implements IStrategy{
    @Override
    public double doCalculate(double money) {
        System.out.println("普通用户");
        return money;
    }
}
