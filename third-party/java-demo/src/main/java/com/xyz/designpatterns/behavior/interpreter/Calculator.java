package com.xyz.designpatterns.behavior.interpreter;

import java.util.Stack;

/**
 * Created by hzhsg on 2018/5/8.
 */
public class Calculator {

    private Stack<Expression> stack = new Stack<>();

    private String expression;

    public Calculator(String expression) {
        this.expression = expression;
        build();
    }

    public String getExpression() {
        return expression;
    }

    private void build(){
        Expression left = null;
        Expression right = null;
        String[] datas = expression.split(" ");
        for(int i=0;i<datas.length;i++){
            switch (datas[i].charAt(0)){
                case '+':
                    left = stack.pop();
                    right = new NumExpression(Double.valueOf(datas[++i]));
                    stack.push(new PlusExpression(left,right));
                    break;
                case '-':
                    left = stack.pop();
                    right = new NumExpression(Double.valueOf(datas[++i]));
                    stack.push(new MinusExpression(left,right));
                    break;
                default:
                    stack.push(new NumExpression(Double.valueOf(datas[i])));
                    break;
            }
        }
    }

    public double calculate(){
        return stack.pop().interpret();
    }
}
