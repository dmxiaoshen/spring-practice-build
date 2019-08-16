package com.xyz.designpatterns.behavior.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhsg on 2018/5/7.
 */
public class BeijingCompany<T> implements Company<T>{

    private List<T> objectList = new ArrayList<>();

    public void addObject(T t){
        objectList.add(t);
    }


    @Override
    public Iterator<T> iterator() {
        return new BeijingIterator(objectList);
    }
}
