package com.xyz.designpatterns.structure.adapter;

/**
 * Created by hzhsg on 2018/4/28.
 * 已有手机，需要5v电压充电
 */
public class Mobile {

    public void charging(V5 v5){
        System.out.println("电压是"+v5.output5v()+"v,开始充电");
    }
}
