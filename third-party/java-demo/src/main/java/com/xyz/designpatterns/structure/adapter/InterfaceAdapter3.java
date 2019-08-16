package com.xyz.designpatterns.structure.adapter;

/**
 * Created by hzhsg on 2018/5/2.
 * 接口适配器
 * 当不需要全部实现接口提供的方法时，可先设计一个抽象类实现接口，并为该接口中每个方法提供一个默认实现（空方法），
 * 那么该抽象类的子类可有选择地覆盖父类的某些方法来实现需求，它适用于一个接口不想使用其所有的方法的情况。
 */
public class InterfaceAdapter3 {

    private Listener listener;

    public InterfaceAdapter3(Listener listener) {
        this.listener = listener;
    }

    public void run(){
        listener.start();
        listener.change();
        listener.pause();
        listener.stop();
    }
}
