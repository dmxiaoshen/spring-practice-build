package com.xyz.designpatterns.behavior.visitor;

/**
 * Created by hzhsg on 2018/5/11.
 */
public class ConsumeBill implements Bill{

    private double amount;
    private String item;

    public ConsumeBill(double amount, String item) {
        this.amount = amount;
        this.item = item;
    }

    @Override
    public void accept(Viewer viewer) {
        viewer.view(this);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
