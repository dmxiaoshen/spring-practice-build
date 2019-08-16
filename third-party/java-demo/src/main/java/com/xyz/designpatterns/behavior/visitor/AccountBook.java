package com.xyz.designpatterns.behavior.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhsg on 2018/5/11.
 */
public class AccountBook {

    private List<Bill> billList = new ArrayList<>();

    public void addBill(Bill bill){
        billList.add(bill);
    }

    public void show(Viewer viewer){
        for(Bill bill:billList){
            bill.accept(viewer);
        }
    }
}
