package com.xyz.designpatterns.creational.singleton;

/**
 * Created by hzhsg on 2018/4/28.
 * 懒汉式，加了synchronized，多线程问题没了，但会导致等待锁，程序执行效率有影响
 */
public class SingletonLV2 {
    private static SingletonLV2 instance;

    private SingletonLV2(){}

    public static synchronized SingletonLV2 getInstance(){
        if(instance==null){
            instance = new SingletonLV2();
        }
        return instance;
    }
}
