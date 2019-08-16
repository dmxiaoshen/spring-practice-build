package com.xyz.designpatterns.structure.flyweight;

/**
 * Created by hzhsg on 2018/5/7.
 */
public class Club {

    private String name;

    public Club(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("社团组建成功:"+getName());
    }
}
