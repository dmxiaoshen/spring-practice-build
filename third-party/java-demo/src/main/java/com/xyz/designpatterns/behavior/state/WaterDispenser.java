package com.xyz.designpatterns.behavior.state;

/**
 * Created by hzhsg on 2018/5/9.
 */
public class WaterDispenser {

    private int capacity;
    private int present;
    private State state;

    public WaterDispenser(int capacity) {
        this.capacity = capacity;
        this.present = this.capacity;
        state = new FullState(this);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void getWater(){
        state.press();
        if(present>0) {
            System.out.println("----获取一次水");
            present--;
        }
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
