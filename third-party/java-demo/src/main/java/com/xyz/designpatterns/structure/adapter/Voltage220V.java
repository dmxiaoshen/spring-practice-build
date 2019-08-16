package com.xyz.designpatterns.structure.adapter;

/**
 * Created by hzhsg on 2018/4/28.
 * 已有220v电压
 */
public class Voltage220V {

    public int output220v(){
        int v = 220;
        System.out.println("这里是"+v+"v");
        return v;
    }
}
