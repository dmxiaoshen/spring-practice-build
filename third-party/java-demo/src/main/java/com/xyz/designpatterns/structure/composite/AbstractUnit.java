package com.xyz.designpatterns.structure.composite;

/**
 * Created by hzhsg on 2018/5/7.
 */
public abstract class AbstractUnit implements Unit{

    protected String name;

    public AbstractUnit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void add(Unit unit) {

    }

    @Override
    public void remove(Unit unit) {

    }

    @Override
    public void show(int level) {
        System.out.println(buildPrefix(level)+getName());
    }

    @Override
    public void sendmail(int level) {
        System.out.println(buildPrefix(level)+getName()+"-收到邮件");
    }

    protected String buildPrefix(int level){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<level;i++){
            builder.append("-");
        }
        return builder.toString();
    }
}
