package com.xyz.designpatterns.behavior.command;

/**
 * Created by hzhsg on 2018/5/3.
 */
public abstract class AbstractCommand implements Command{

    protected boolean isOn = false;

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
