package com.xyz.designpatterns.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhsg on 2018/5/7.
 * 微信公众号
 */
public class WeChatPublic implements Observable{

    private List<Observer> observerList = new ArrayList<>();
    private String message;

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyAllObserver() {
        for(Observer observer : observerList){
            observer.notify(message);
        }
    }

    public void pushMessage(String message){
        System.out.println("微信公众号推送了一条消息:"+message);
        this.message = message;
        notifyAllObserver();
    }
}
