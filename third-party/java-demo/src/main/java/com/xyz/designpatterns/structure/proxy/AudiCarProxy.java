package com.xyz.designpatterns.structure.proxy;

import java.util.Random;

/**
 * Created by hzhsg on 2018/5/7.
 */
public class AudiCarProxy implements Car{
    private AudiCar audiCar;

    public AudiCarProxy(AudiCar audiCar) {
        this.audiCar = audiCar;
    }

    @Override
    public void run() {
        Random random = new Random();
        if(random.nextBoolean()){
            System.out.println("代理类允许奥迪车启动");
            audiCar.run();
        }else{
            System.out.println("代理类不允许奥迪车启动");
        }
    }
}
