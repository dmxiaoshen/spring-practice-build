package com.xyz.designpatterns.creational.singleton;

/**
 * Created by hzhsg on 2018/4/28.
 * 这种写法在功能上与共有域方法相近，但是它更简洁，无偿地提供了序列化机制，
 * 绝对防止对此实例化，即使是在面对复杂的序列化或者反射攻击的时候。
 * 虽然这中方法还没有广泛采用，但是单元素的枚举类型已经成为实现Singleton的最佳方法。
 */
public enum SingletonLV6 {
    INSTANCE;

    public void f(){
        System.out.println("enum singleton");
    }
}
