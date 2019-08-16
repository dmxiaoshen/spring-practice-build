package com.xyz.designpatterns.creational.factory;

/**
 * Created by hzhsg on 2018/5/2.
 */
public class SimpleShapeFactory {

    public static Shape getShape(String type){
        if("circle".equals(type)){
            return new Circle();
        }else if("rectangle".equals(type)){
            return new Rectangle();
        }else if("triangle".equals(type)){
            return new Triangle();
        }else{
            return null;
        }
    }
}
