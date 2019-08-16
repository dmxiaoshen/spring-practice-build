package com.xyz.designpatterns.structure.bridge;

/**
 * Created by hzhsg on 2018/5/4.
 */
public class Charger5V implements Charger{

    @Override
    public int voltage() {
        return 5;
    }
}
