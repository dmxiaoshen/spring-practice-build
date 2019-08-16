package com.xyz.designpatterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhsg on 2018/5/3.
 */
public class Student1 implements Cloneable{

    private String name;
    private int age;
    private List<String> nickNames;
    private List<Book> bookList;
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

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
    public Student1 clone() {
        Student1 student1 = null;
        try {
            //手动深拷贝
            student1 = (Student1) super.clone();
            student1.nickNames = (ArrayList<String>)((ArrayList<String>)this.nickNames).clone();
            student1.bookList = (ArrayList<Book>)((ArrayList<Book>)this.bookList).clone();
            //list里面的book不需要实现Cloneable接口也能深拷贝，但是如果单独book对象的话，要实现Cloneable接口
            student1.book = this.book.clone();
        }catch (CloneNotSupportedException e){
            throw new RuntimeException(e);
        }
        return student1;
    }
}