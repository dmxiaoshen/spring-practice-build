package com.xyz.designpatterns.structure.adapter;

/**
 * Created by hzhsg on 2018/4/28.
 * 适配器模式
 */
public class Main {

    public static void main(String[] args) {
        testVoltageAdapter1();
        testVoltageAdapter2();
        testInterfaceAdapter3();

    }

    /**
     * 类适配,继承src,dst必须是接口，局限性很大
     */
    public static void testVoltageAdapter1(){
        Mobile mobile = new Mobile();
        mobile.charging(new VoltageAdapter1());
    }

    /**
     * 对象适配，持有 src类，实现 dst 类接口，完成src->dst的适配。
     */
    public static void testVoltageAdapter2(){
        Mobile mobile = new Mobile();
        mobile.charging(new VoltageAdapter2(new Voltage220V()));
    }

    /**
     * 接口适配器
     */
    public static void testInterfaceAdapter3(){
        InterfaceAdapter3 interfaceAdapter3 = new InterfaceAdapter3(new ListenerAdapter() {
            @Override
            public void start() {
                System.out.println("I am start");
            }

            @Override
            public void stop() {
                System.out.println("I am stop");
            }
        });
        interfaceAdapter3.run();
    }
}
