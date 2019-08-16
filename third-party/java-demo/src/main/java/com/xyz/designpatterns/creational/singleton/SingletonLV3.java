package com.xyz.designpatterns.creational.singleton;

/**
 * Created by hzhsg on 2018/4/28.
 * 懒汉式，双重检查版本，针对V2版的优化，只在instance==null的时候触发锁机制，以便提高程序执行效率
 */
public class SingletonLV3 {
    private static SingletonLV3 instance;

    private SingletonLV3(){}

    public static SingletonLV3 getInstance(){
        if(instance==null){
            synchronized (SingletonLV3.class){
                if(instance==null){
                    instance = new SingletonLV3();
                }
            }

        }
        return instance;
    }
}
