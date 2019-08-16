package com.xyz.designpatterns.behavior.chain.fliter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhsg on 2018/5/10.
 */
public class FliterChain implements Fliter{

    private List<Fliter> fliters = new ArrayList<>();

    private int index = 0;

    public FliterChain addFliter(Fliter fliter){
        fliters.add(fliter);
        return this;
    }

    @Override
    public void doFliter(Request request, Response response, FliterChain fliterChain) {
        if(fliters.size()==index){
            if(index==0) {
                response.setResponseStr(request.getRequestStr());
            }
            return;
        }
        Fliter fliter = fliters.get(index);
        index++;
        fliter.doFliter(request,response,fliterChain);
    }
}
