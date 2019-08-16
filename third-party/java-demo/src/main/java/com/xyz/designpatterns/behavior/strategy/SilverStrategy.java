package com.xyz.designpatterns.behavior.strategy;

/**
 * Created by hzhsg on 2018/5/9.
 */
public class SilverStrategy implements IStrategy{
    @Override
    public double doCalculate(double money) {
        System.out.println("白银会员");
        return money*0.9;
    }
}
