package com.xyz.designpatterns.behavior.visitor;

/**
 * Created by hzhsg on 2018/5/11.
 */
public class CFOViewer implements Viewer{
    @Override
    public void view(ConsumeBill bill) {
        System.out.println("主管查看，支出金额:"+bill.getAmount()+"支出项目:"+bill.getItem());
    }

    @Override
    public void view(IncomeBill bill) {
        System.out.println("主管查看，收入金额:"+bill.getAmount()+"收入项目:"+bill.getItem());
    }
}
