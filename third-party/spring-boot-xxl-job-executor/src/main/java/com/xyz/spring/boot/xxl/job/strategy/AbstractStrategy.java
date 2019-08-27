package com.xyz.spring.boot.xxl.job.strategy;

import lombok.Data;

@Data
public abstract class AbstractStrategy implements IStrategy{

    protected String name;

    protected AbstractStrategy(String name){
        this.name = name;
    }

}
