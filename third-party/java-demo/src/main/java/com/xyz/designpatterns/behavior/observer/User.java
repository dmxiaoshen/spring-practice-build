package com.xyz.designpatterns.behavior.observer;

/**
 * Created by hzhsg on 2018/5/7.
 */
public class User implements Observer{

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void notify(String message) {
        System.out.println(name+"收到推送消息:"+message);
    }
}
