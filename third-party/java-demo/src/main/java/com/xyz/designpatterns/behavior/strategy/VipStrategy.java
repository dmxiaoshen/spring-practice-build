package com.xyz.designpatterns.behavior.strategy;

/**
 * Created by hzhsg on 2018/5/9.
 */
public class VipStrategy implements IStrategy{
    @Override
    public double doCalculate(double money) {
        System.out.println("VIP用户");
        return money*0.7;
    }
}
