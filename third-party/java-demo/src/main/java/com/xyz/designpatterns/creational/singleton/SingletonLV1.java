package com.xyz.designpatterns.creational.singleton;

/**
 * Created by hzhsg on 2018/4/28.
 * 懒汉式，延迟加载，但是该版本有多线程问题
 */
public class SingletonLV1 {
    private static SingletonLV1 instance;

    private SingletonLV1(){}

    public static SingletonLV1 getInstance(){
        if(instance==null){
            instance = new SingletonLV1();
        }
        return instance;
    }
}
