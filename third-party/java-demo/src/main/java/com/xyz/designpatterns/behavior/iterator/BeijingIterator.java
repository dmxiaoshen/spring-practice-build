package com.xyz.designpatterns.behavior.iterator;

import java.util.List;

/**
 * Created by hzhsg on 2018/5/7.
 * 外部迭代器
 */
public class BeijingIterator<T> implements Iterator<T>{
    private List<T> list;
    private int pos = 0;

    public BeijingIterator(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return !(pos>list.size()-1 || list.get(pos)==null);
    }

    @Override
    public T next() {
        T t = list.get(pos);
        pos++;
        return t;
    }
}
