package com.xyz.designpatterns.behavior.observer;

/**
 * Created by hzhsg on 2018/5/7.
 * 观察者需实现的接口
 */
public interface Observer {

    //所有观察者被回调的方法(俗称信息通知)
    void notify(String message);
}
