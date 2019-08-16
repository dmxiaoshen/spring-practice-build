package com.xyz.designpatterns.behavior.memo.white;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhsg on 2018/5/8.
 */
public class Client {

    private List<String> statesList = new ArrayList<>();
    private int index = 0;


    public void setState(String state){
        statesList.add(state);
        index++;
    }

    public Memo createMemo(){
        return new Memo(statesList,index);
    }

    public void reverseMemo(Memo memo){
        this.statesList = memo.getStatesList();
        this.index = memo.getIndex();
    }

    public void show(){
        System.out.println("当前有"+index+"个检查点");
        statesList.stream().forEach(t-> System.out.println(t));
    }
}
