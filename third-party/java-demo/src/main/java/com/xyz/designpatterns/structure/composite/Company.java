package com.xyz.designpatterns.structure.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhsg on 2018/5/7.
 */
public class Company extends AbstractUnit{

    private List<Unit> unitList = new ArrayList<>();

    public Company(String name) {
        super(name);
    }

    @Override
    public void add(Unit unit) {
        unitList.add(unit);
    }

    @Override
    public void remove(Unit unit) {
        unitList.remove(unit);
    }

    @Override
    public void show(int level) {
        System.out.println(buildPrefix(level)+getName());
        for(Unit unit : unitList){
            unit.show(level+2);
        }

    }

    @Override
    public void sendmail(int level) {
        System.out.println(buildPrefix(level)+getName()+"-收到邮件");
        for(Unit unit:unitList){
            unit.sendmail(level+2);
        }
    }
}
