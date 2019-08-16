package com.xyz.designpatterns.behavior.iterator;

/**
 * Created by hzhsg on 2018/5/7.
 */
public interface Company<T> {

    Iterator<T> iterator();
}
