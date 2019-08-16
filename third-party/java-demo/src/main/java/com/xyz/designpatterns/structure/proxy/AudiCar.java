package com.xyz.designpatterns.structure.proxy;

/**
 * Created by hzhsg on 2018/5/7.
 */
public class AudiCar implements Car{

    @Override
    public void run() {
        System.out.println("奥迪车启动");
    }
}
