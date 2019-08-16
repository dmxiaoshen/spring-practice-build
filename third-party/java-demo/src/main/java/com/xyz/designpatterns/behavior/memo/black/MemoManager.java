package com.xyz.designpatterns.behavior.memo.black;

/**
 * Created by hzhsg on 2018/5/8.
 */
public class MemoManager {

    private IMemo memo;

    public IMemo getMemo() {
        return memo;
    }

    public void setMemo(IMemo memo) {
        this.memo = memo;
    }
}
