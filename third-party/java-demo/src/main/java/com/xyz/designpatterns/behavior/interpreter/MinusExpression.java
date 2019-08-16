package com.xyz.designpatterns.behavior.interpreter;

/**
 * Created by hzhsg on 2018/5/8.
 */
public class MinusExpression implements Expression {

    private Expression left;
    private Expression right;

    public MinusExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double interpret() {
        return left.interpret()-right.interpret();
    }
}
