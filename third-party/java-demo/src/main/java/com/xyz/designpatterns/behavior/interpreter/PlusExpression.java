package com.xyz.designpatterns.behavior.interpreter;

/**
 * Created by hzhsg on 2018/5/8.
 */
public class PlusExpression implements Expression{
    private Expression left;
    private Expression right;

    public PlusExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double interpret() {
        return left.interpret()+right.interpret();
    }
}
