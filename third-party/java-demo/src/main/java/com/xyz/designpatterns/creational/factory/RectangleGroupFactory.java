package com.xyz.designpatterns.creational.factory;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class RectangleGroupFactory implements AbstractFactory{
    @Override
    public Shape createShape() {
        return new Rectangle();
    }

    @Override
    public Button createButton() {
        return new RectangleButton();
    }
}
