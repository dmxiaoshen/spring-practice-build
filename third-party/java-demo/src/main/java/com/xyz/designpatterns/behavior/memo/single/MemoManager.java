package com.xyz.designpatterns.behavior.memo.single;

/**
 * Created by hzhsg on 2018/5/8.
 */
public class MemoManager {

    private Memo memo;

    /**
     * 取备忘录
     * @return
     */
    public Memo getMemo() {
        return memo;
    }

    /**
     * 保存备忘录
     * @param memo
     */
    public void setMemo(Memo memo) {
        this.memo = memo;
    }
}
