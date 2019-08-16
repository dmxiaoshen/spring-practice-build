package com.xyz.designpatterns.creational.factory;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class CircleFactory implements ShapeFactory{
    @Override
    public Shape getShape() {
        return new Circle();
    }
}
