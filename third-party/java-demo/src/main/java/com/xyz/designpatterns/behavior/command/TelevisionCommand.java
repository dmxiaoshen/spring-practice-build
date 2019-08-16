package com.xyz.designpatterns.behavior.command;

/**
 * Created by hzhsg on 2018/5/3.
 */
public class TelevisionCommand extends AbstractCommand{

    private Television television;

    public TelevisionCommand(Television television) {
        this.television = television;
    }

    @Override
    public void execute() {
        television.on();
    }

    @Override
    public void undo() {
        television.off();
    }
}
