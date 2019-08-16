package com.xyz.designpatterns.creational.factory;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class TriangleGroupFactory implements AbstractFactory {
    @Override
    public Shape createShape() {
        return new Triangle();
    }

    @Override
    public Button createButton() {
        return new TriangleButton();
    }
}
