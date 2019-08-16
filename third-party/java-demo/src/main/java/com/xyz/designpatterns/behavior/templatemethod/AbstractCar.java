package com.xyz.designpatterns.behavior.templatemethod;

/**
 * Created by hzhsg on 2018/5/2.
 * 定义了一个算法的骨架，而将一些步骤延迟到子类中，模版方法使得子类可以在不改变算法结构的情况下，重新定义算法的步骤。
 */
public abstract class AbstractCar {

    public void run(){
        ignition();
        start();
        if(isAlarm()){
            alarm();
        }
        stop();
    }

    protected  abstract void ignition();

    protected abstract void start();

    protected boolean isAlarm(){
        return true;
    }

    protected abstract void stop();

    protected abstract void alarm();
}
