package com.xyz.designpatterns.behavior.chain;

/**
 * Created by hzhsg on 2018/5/10.
 */
public class Boss implements Person{
    @Override
    public boolean loan(double money) {
        if(money>500000){
            System.out.println("金额太大，老板不予借款"+money+"元");
            return false;
        }else{
            System.out.println("老板同意，借款"+money+"元");
            return true;
        }
    }
}
