package com.xyz.designpatterns.creational.factory;

/**
 * Created by hzhsg on 2018/5/2.
 */
public interface AbstractFactory {

    Shape createShape();

    Button createButton();
}
