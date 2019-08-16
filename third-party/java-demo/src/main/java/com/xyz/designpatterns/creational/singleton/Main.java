package com.xyz.designpatterns.creational.singleton;

/**
 * Created by hzhsg on 2018/4/28.
 * 单例模式
 */
public class Main {

    public static void main(String[] args) {
        //testSingletonE();
        //testSingletonLV1();
        //testSingletonLV2();
        //testSingletonLV3();
        //testSingletonLV4();
        //testSingletonLV5();
        testSingletonLV6();
    }

    public static void testSingletonLV6(){
        //
        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     */
                    SingletonLV6 lv6 = SingletonLV6.INSTANCE;
                    System.out.println(lv6);
                    lv6.f();
                }
            }).start();
        }
    }

    public static void testSingletonLV5(){
        //
        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     */
                    System.out.println(SingletonLV5.getInstance());
                }
            }).start();
        }
    }

    public static void testSingletonLV4(){
        //
        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     */
                    System.out.println(SingletonLV4.getInstance());
                }
            }).start();
        }
    }

    public static void testSingletonLV3(){
        //
        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     */
                    System.out.println(SingletonLV3.getInstance());
                }
            }).start();
        }
    }

    public static void testSingletonLV2(){
        //
        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     */
                    System.out.println(SingletonLV2.getInstance());
                }
            }).start();
        }
    }

    public static void testSingletonLV1(){
        //多线程的时候，会出现不同实例
        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     * com.dmxiaoshen.creational.singleton.SingletonLV1@5779465d
                     * com.dmxiaoshen.creational.singleton.SingletonLV1@6a531405
                     * com.dmxiaoshen.creational.singleton.SingletonLV1@6a531405
                     * com.dmxiaoshen.creational.singleton.SingletonLV1@6a531405
                     */
                    System.out.println(SingletonLV1.getInstance());
                }
            }).start();
        }
    }

    public static void testSingletonE(){
        //天生线程安全
        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     * com.dmxiaoshen.creational.singleton.SingletonE@2dd12e59
                     * com.dmxiaoshen.creational.singleton.SingletonE@2dd12e59
                     * com.dmxiaoshen.creational.singleton.SingletonE@2dd12e59
                     * com.dmxiaoshen.creational.singleton.SingletonE@2dd12e59
                     */
                    System.out.println(SingletonE.getInstance());
                }
            }).start();
        }
    }
}
