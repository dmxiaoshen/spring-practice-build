package com.xyz.designpatterns.creational.prototype;

import java.io.*;
import java.util.List;

/**
 * Created by hzhsg on 2018/5/3.
 */
public class Student2 implements Serializable{

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


    /**
     * 二进制流重建对象实现深拷贝
     * 把对象写到流里的过程是串行化（Serilization）过程，
     * 但是在Java程序师圈子里又非常形象地称为“冷冻”或者“腌咸菜（picking）”过程；
     * 而把对象从流中读出来的并行化（Deserialization）过程则叫做“解冻”或者“回鲜(depicking)”过程。
     * 应当指出的是，写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面，因此“腌成咸菜”的只是对象的一个拷贝，
     * Java咸菜还可以回鲜。
     * 在Java语言里深复制一个对象，常常可以先使对象实现Serializable接口，
     * 然后把对象（实际上只是对象的一个拷贝）写到一个流里（腌成咸菜），
     * 再从流里读出来（把咸菜回鲜），便可以重建对象。
     * @return
     */
    public Student2 deepClone(){
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(stream);
            out.writeObject(this);
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(stream.toByteArray()));
            return (Student2)in.readObject();
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}