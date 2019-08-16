package com.xyz.designpatterns.behavior.observer;

/**
 * Created by hzhsg on 2018/5/7.
 * 被观察者需实现的接口
 */
public interface Observable {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyAllObserver();
}
