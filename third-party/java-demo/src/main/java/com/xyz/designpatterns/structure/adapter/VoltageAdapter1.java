package com.xyz.designpatterns.structure.adapter;

/**
 * Created by hzhsg on 2018/4/28.
 * 类适配,继承src,dst必须是接口，局限性很大
 */
public class VoltageAdapter1 extends Voltage220V implements V5{
    @Override
    public int output5v() {
        int v = output220v();
        v = v/44;
        System.out.println("VoltageAdapter1适配后输出电压"+v+"v");
        return v;
    }
}
