package com.xyz.designpatterns.behavior.memo.white;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhsg on 2018/5/8.
 */
public class MemoManager {
    private List<Memo> memos = new ArrayList<>();

    public void storeMemo(Memo memo){
        memos.add(memo);
    }

    public Memo getMemo(int index){
        return memos.get(index);
    }
}
