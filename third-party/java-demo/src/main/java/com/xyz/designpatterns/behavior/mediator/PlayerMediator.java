package com.xyz.designpatterns.behavior.mediator;

/**
 * Created by hzhsg on 2018/5/7.
 */
public class PlayerMediator {

    private Player a;
    private Player b;

    public Player getA() {
        return a;
    }

    public void setA(Player a) {
        this.a = a;
    }

    public Player getB() {
        return b;
    }

    public void setB(Player b) {
        this.b = b;
    }

    public void win(int money,String name){
        if(a.getName().equals(name)){
            a.setMoney(a.getMoney()+money);
            b.setMoney(b.getMoney()-money);
        }else{
            a.setMoney(a.getMoney()-money);
            b.setMoney(b.getMoney()+money);
        }
    }


}
