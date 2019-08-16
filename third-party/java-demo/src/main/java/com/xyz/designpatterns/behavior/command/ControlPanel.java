package com.xyz.designpatterns.behavior.command;

/**
 * Created by hzhsg on 2018/5/3.
 */
public class ControlPanel {

    private AbstractCommand[] buttons = new AbstractCommand[9];

    public void addCommand(int i, AbstractCommand command){
        buttons[i] = command;
    }

    public void pressButton(int i){
        AbstractCommand temp = buttons[i];
        if(temp.isOn()){
            temp.undo();
        }else{
            temp.execute();
        }
        temp.setOn(!temp.isOn());
    }
}
