package com.xyz.designpatterns.creational.singleton;

/**
 * Created by hzhsg on 2018/4/28.
 *  饿汉式，类装载时构建实例，天生就线程安全
 */
public class SingletonE {

    private static final SingletonE INSTANCE = new SingletonE();

    private SingletonE() {}

    public static SingletonE getInstance(){
        return INSTANCE;
    }
}
