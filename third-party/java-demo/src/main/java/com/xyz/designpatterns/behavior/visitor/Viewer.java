package com.xyz.designpatterns.behavior.visitor;

/**
 * Created by hzhsg on 2018/5/11.
 */
public interface Viewer {

    void view(ConsumeBill bill);

    void view(IncomeBill bill);
}
