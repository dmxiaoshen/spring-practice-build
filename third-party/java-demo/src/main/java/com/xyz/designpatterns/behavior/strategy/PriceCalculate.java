package com.xyz.designpatterns.behavior.strategy;

/**
 * Created by hzhsg on 2018/5/9.
 */
public class PriceCalculate {

    private IStrategy strategy;

    public IStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(double money){
        return strategy.doCalculate(money);
    }
}
