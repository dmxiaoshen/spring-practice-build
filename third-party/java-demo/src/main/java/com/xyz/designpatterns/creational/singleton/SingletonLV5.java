package com.xyz.designpatterns.creational.singleton;

/**
 * Created by hzhsg on 2018/4/28.
 * 这样的写法非常巧妙，应该来说是最佳实践，内部holder类用了饿汉，外部来看又是懒汉
 */
public class SingletonLV5 {

    private static class SingletonLV5Holder{
        private static final SingletonLV5 INSTANCE = new SingletonLV5();
    }

    private SingletonLV5(){}

    public static final SingletonLV5 getInstance(){
        return SingletonLV5Holder.INSTANCE;
    }
}
