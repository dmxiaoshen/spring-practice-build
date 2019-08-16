package com.xyz.designpatterns.behavior.chain.fliter;

/**
 * Created by hzhsg on 2018/5/10.
 */
public interface Fliter {

    void doFliter(Request request, Response response, FliterChain fliterChain);
}
