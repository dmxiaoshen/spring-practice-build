package com.xyz.designpatterns.creational.factory;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class CircleGroupFactory implements AbstractFactory{
    @Override
    public Shape createShape() {
        return new Circle();
    }

    @Override
    public Button createButton() {
        return new CircleButton();
    }
}
