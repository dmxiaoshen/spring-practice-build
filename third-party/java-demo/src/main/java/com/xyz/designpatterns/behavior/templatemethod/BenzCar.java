package com.xyz.designpatterns.behavior.templatemethod;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class BenzCar extends AbstractCar{

    private boolean isAlarm;

    public BenzCar(boolean isAlarm) {
        this.isAlarm = isAlarm;
    }

    @Override
    protected boolean isAlarm() {
        return this.isAlarm;
    }

    @Override
    protected void ignition() {
        System.out.println("Benz点火");
    }

    @Override
    protected void start() {
        System.out.println("Benz启动");
    }

    @Override
    protected void stop() {
        System.out.println("Benz停止");
    }

    @Override
    protected void alarm() {
        System.out.println("Benz鸣笛");
    }
}
