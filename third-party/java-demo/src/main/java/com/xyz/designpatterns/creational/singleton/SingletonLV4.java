package com.xyz.designpatterns.creational.singleton;

/**
 * Created by hzhsg on 2018/4/28.
 * 预先了解:原子操作，指令重排
 * 懒汉式，加了volatile关键字，因为V3版本看起来是完美了，但是因为jvm即时编译存在指令重排，导致线程T1对instance的写操作没有完成，线程T2就执行了读操作
 *
 * volatile关键字的一个作用是禁止指令重排，把instance声明为volatile之后，对它的写操作就会有一个内存屏障（什么是内存屏障？），这样，在它的赋值完成之前，就不用会调用读操作。
 * 注意：volatile阻止的不singleton = new Singleton()这句话内部[1-2-3]的指令重排，
 * 而是保证了在一个写操作（[1-2-3]）完成之前，
 * 不会调用读操作（if (instance == null)）。
 */
public class SingletonLV4 {
    private static volatile SingletonLV4 instance;

    private SingletonLV4(){}

    public static SingletonLV4 getInstance(){
        if(instance==null){
            synchronized (SingletonLV4.class){
                if(instance==null){
                    instance = new SingletonLV4();
                }
            }
        }
        return instance;
    }
}
