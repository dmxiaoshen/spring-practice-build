package com.xyz.designpatterns.behavior.visitor;

/**
 * Created by hzhsg on 2018/5/11.
 */
public interface Bill {

    void accept(Viewer viewer);
}
