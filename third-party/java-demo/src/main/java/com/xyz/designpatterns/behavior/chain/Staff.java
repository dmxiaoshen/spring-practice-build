package com.xyz.designpatterns.behavior.chain;

/**
 * Created by hzhsg on 2018/5/10.
 */
public class Staff implements Person{

    private Person next;

    public Person getNext() {
        return next;
    }

    public void setNext(Person next) {
        this.next = next;
    }

    @Override
    public boolean loan(double money) {
        if(money>100000){
            if(next!=null) {
                return next.loan(money);
            }
            System.out.println("普通业务人拒绝意借款"+money+"元");
            return false;
        }else{
            System.out.println("普通业务人员同意借款"+money+"元");
            return true;
        }
    }
}
