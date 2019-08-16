package com.xyz.designpatterns.behavior.memo.single;

/**
 * Created by hzhsg on 2018/5/8.
 */
public class Memo {
    private String state;

    public Memo(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
