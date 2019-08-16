package com.xyz.designpatterns.behavior.command;

/**
 * Created by hzhsg on 2018/5/3.
 */
public class RefrigeratorCommand extends AbstractCommand{

    private Refrigerator refrigerator;

    public RefrigeratorCommand(Refrigerator refrigerator) {
        this.refrigerator = refrigerator;
    }

    @Override
    public void execute() {
        refrigerator.on();
    }

    @Override
    public void undo() {
        refrigerator.off();
    }
}
