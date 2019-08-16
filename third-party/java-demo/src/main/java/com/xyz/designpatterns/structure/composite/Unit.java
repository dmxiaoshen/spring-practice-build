package com.xyz.designpatterns.structure.composite;

/**
 * Created by hzhsg on 2018/5/7.
 */
public interface Unit {

    void add(Unit unit);

    void remove(Unit unit);

    void show(int level);

    void sendmail(int level);
}
