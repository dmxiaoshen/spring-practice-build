package com.xyz.designpatterns.behavior.templatemethod;

/**
 * Created by hzhsg on 2018/5/2.
 * 模板方法模式
 */
public class Main {

    public static void main(String[] args) {
        AudiCar audi = new AudiCar(false);
        audi.run();
        BenzCar benz = new BenzCar(true);
        benz.run();
    }
}
