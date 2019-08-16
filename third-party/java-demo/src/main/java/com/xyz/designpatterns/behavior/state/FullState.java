package com.xyz.designpatterns.behavior.state;

/**
 * Created by hzhsg on 2018/5/9.
 */
public class FullState implements State{

    private WaterDispenser waterDispenser;

    public FullState(WaterDispenser waterDispenser) {
        this.waterDispenser = waterDispenser;
    }

    @Override
    public void press() {
        if(waterDispenser.getPresent()<waterDispenser.getCapacity()/2){
            waterDispenser.setState(new HalfState(waterDispenser));
            waterDispenser.getState().press();
        }else{
            System.out.println("饮水机很充足，获取水");
        }
    }
}
