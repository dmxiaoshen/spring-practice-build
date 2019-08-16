package com.xyz.designpatterns.creational.prototype;

import java.io.Serializable;

/**
 * Created by hzhsg on 2018/5/3.
 */
public class Book implements Cloneable,Serializable{

    private String name;
    private double price;

    public Book() {
    }

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public Book clone() {
        Book book = null;
        try {
            book = (Book) super.clone();
        }catch (CloneNotSupportedException e){
            throw new RuntimeException(e);
        }
        return book;
    }
}
