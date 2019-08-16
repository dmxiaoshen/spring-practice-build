package com.xyz.designpatterns.behavior.memo.single;

/**
 * Created by hzhsg on 2018/5/8.
 */
public class Client {

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memo createMemo(){
        return new Memo(state);
    }

    public void reverseMemo(Memo memo){
        this.state = memo.getState();
    }

    public void show(){
        System.out.println("Client state is: "+state);
    }
}
