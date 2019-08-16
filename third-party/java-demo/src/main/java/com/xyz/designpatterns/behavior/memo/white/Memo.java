package com.xyz.designpatterns.behavior.memo.white;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhsg on 2018/5/8.
 */
public class Memo {

    private List<String> statesList = new ArrayList<>();
    private int index = 0;

    public Memo(List<String> statesList, int index) {
        this.statesList = new ArrayList<>(statesList);
        this.index = index;
    }

    public List<String> getStatesList() {
        return statesList;
    }

    public void setStatesList(List<String> statesList) {
        this.statesList = statesList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
