package com.xyz.designpatterns.behavior.templatemethod;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class AudiCar extends AbstractCar{

    private boolean isAlarm;

    public AudiCar(boolean isAlarm) {
        this.isAlarm = isAlarm;
    }

    @Override
    protected boolean isAlarm() {
        return this.isAlarm;
    }

    @Override
    protected void ignition() {
        System.out.println("Audi点火");
    }

    @Override
    protected void start() {
        System.out.println("Audi启动");
    }

    @Override
    protected void stop() {
        System.out.println("Audi停止");
    }

    @Override
    protected void alarm() {
        System.out.println("Audi鸣笛");
    }
}
