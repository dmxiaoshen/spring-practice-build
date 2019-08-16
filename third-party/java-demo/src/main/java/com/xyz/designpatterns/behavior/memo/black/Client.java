package com.xyz.designpatterns.behavior.memo.black;

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

    public IMemo createMemo(){
        return new Memo(state);
    }

    public void reverseMemo(IMemo memo){
        this.state = ((Memo)memo).getState();
    }

    private class Memo implements IMemo{
        private String state;

        public Memo(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    public void show(){
        System.out.println("Client state is:"+state);
    }
}
