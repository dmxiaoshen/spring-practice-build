package com.xyz.designpatterns.behavior.state;

/**
 * Created by hzhsg on 2018/5/9.
 */
public class HalfState implements State{

    private WaterDispenser waterDispenser;

    public HalfState(WaterDispenser waterDispenser) {
        this.waterDispenser = waterDispenser;
    }

    @Override
    public void press() {
        if(waterDispenser.getPresent()==0){
            waterDispenser.setState(new EmptyState(waterDispenser));
            waterDispenser.getState().press();
        }else {
            System.out.println("饮水机水量很低,获取水");
        }
    }
}
