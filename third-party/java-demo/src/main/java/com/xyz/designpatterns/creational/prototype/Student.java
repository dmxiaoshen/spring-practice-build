package com.xyz.designpatterns.creational.prototype;

import java.util.List;

/**
 * Created by hzhsg on 2018/5/3.
 */
public class Student implements Cloneable{

    private String name;
    private int age;
    private List<String> nickNames;
    private List<Book> bookList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getNickNames() {
        return nickNames;
    }

    public void setNickNames(List<String> nickNames) {
        this.nickNames = nickNames;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public Student clone() {
        Student student = null;
        try {
            student = (Student) super.clone();
        }catch (CloneNotSupportedException e){
            throw new RuntimeException(e);
        }
        return student;
    }
}
