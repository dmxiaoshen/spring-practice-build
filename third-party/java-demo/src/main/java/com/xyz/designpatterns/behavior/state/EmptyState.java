package com.xyz.designpatterns.behavior.state;

/**
 * Created by hzhsg on 2018/5/9.
 */
public class EmptyState implements State{
    private WaterDispenser waterDispenser;

    public EmptyState(WaterDispenser waterDispenser) {
        this.waterDispenser = waterDispenser;
    }

    @Override
    public void press() {
        if(waterDispenser.getPresent()>0){
            waterDispenser.setState(new FullState(waterDispenser));
            waterDispenser.getState().press();
        }else {
            System.out.println("饮水机没水啦，不要再按啦");
        }
    }
}
