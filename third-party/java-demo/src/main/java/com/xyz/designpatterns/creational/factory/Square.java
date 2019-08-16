package com.xyz.designpatterns.creational.factory;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class Square implements Shape{
    private static final Square INSTANCE = new Square();

    public static Square getInstance(){
        return INSTANCE;
    }
    @Override
    public void draw() {
        System.out.println("Square draw");
    }
}
