package com.xyz.designpatterns.structure.bridge;

/**
 * Created by hzhsg on 2018/5/4.
 */
public class Phone1 implements Phone{
    private Charger charger;

    public Phone1(Charger charger) {
        this.charger = charger;
    }

    @Override
    public void charging() {
        System.out.println(charger.voltage()+"V充电的Phone1手机");
    }
}
