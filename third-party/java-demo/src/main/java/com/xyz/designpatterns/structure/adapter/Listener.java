package com.xyz.designpatterns.structure.adapter;

/**
 * Created by hzhsg on 2018/5/2.
 */
public interface Listener {

    void start();
    void stop();
    void change();
    void pause();
}
