package com.xyz.designpatterns.creational.factory;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class RectangleFactory implements ShapeFactory{
    @Override
    public Shape getShape() {
        return new Rectangle();
    }
}
