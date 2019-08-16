package com.xyz.designpatterns.behavior.mediator;

/**
 * Created by hzhsg on 2018/5/7.
 */
public class Player {

    private String name;

    private int money;

    private PlayerMediator playerMediator;

    public Player(String name, int money, PlayerMediator playerMediator) {
        this.name = name;
        this.money = money;
        this.playerMediator = playerMediator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void win(int money){
        playerMediator.win(money,name);
    }

    public void show(){
        System.out.println("玩家"+getName()+"当前金额为:"+getMoney());
    }
}
