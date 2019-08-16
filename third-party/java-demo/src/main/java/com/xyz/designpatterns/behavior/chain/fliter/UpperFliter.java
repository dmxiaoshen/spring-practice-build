package com.xyz.designpatterns.behavior.chain.fliter;

/**
 * Created by hzhsg on 2018/5/10.
 */
public class UpperFliter implements Fliter{
    @Override
    public void doFliter(Request request, Response response, FliterChain fliterChain) {
        String str = response.getResponseStr();
        if(str==null){
            response.setResponseStr(request.getRequestStr());
        }
        response.setResponseStr(response.getResponseStr().toUpperCase());
        fliterChain.doFliter(request,response,fliterChain);
    }
}
