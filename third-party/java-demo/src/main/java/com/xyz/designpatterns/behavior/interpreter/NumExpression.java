package com.xyz.designpatterns.behavior.interpreter;

/**
 * Created by hzhsg on 2018/5/8.
 */
public class NumExpression implements Expression{
    private double data;

    public NumExpression(double data) {
        this.data = data;
    }

    @Override
    public double interpret() {
        return data;
    }
}
