package com.xyz.spring.boot.xxl.job.strategy;

public class StrategyFactory {

    public static IStrategy build(String name){
        if("xhdq".equalsIgnoreCase(name)){
            return new XhdqStrategy(name);
        }else if("lssdjt".equalsIgnoreCase(name)){
            return new TodayHisStrategy(name);
        }else{
            return new NormalStrategy(name);
        }
    }
}
