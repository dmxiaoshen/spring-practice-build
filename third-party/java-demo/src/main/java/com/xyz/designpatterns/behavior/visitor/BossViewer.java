package com.xyz.designpatterns.behavior.visitor;

/**
 * Created by hzhsg on 2018/5/11.
 */
public class BossViewer implements Viewer{

    private double totalConsume;
    private double totalIncome;

    @Override
    public void view(ConsumeBill bill) {
        totalConsume+=bill.getAmount();
    }

    @Override
    public void view(IncomeBill bill) {
        totalIncome+=bill.getAmount();
    }

    public void show(){
        System.out.println("老板查看，一共收入"+totalIncome+",一共支出"+totalConsume);
    }
}
