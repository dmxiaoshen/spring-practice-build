package com.xyz.designpatterns.creational.prototype;

import com.xyz.designpatterns.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhsg on 2018/5/3.
 * 原型模式
 * 1、原型模式向客户隐藏了创建对象的复杂性。客户只需要知道要创建对象的类型，
 * 然后通过请求就可以获得和该对象一模一样的新对象，无须知道具体的创建过程。
 * 2、克隆分为浅克隆和深克隆两种。
 * 3、我们虽然可以利用原型模式来获得一个新对象，但有时对象的复制可能会相当的复杂，比如深克隆。
 */
public class Main {
    public static void main(String[] args) {
        //testPrototype();
        //testShallowCopy();
        //testDeepClone();
        testStreamDeepClone();
    }

    /**
     * 优点： 1、性能提高。 2、逃避构造函数的约束。
     * 缺点： 1、配备克隆方法需要对类的功能进行通盘考虑，这对于全新的类不是很难，但对于已有的类不一定很容易，
     * 特别当一个类引用不支持串行化的间接对象，或者引用含有循环结构的时候。
     * 2、必须实现 Cloneable 接口。
     */
    public static void testPrototype() {
        Resume resume = new Resume();
        resume.setName("Lilei");
        resume.setPhone("13889565873");
        resume.setAge(26);
        resume.setAddress("苏州");
        System.out.println(resume.toString());
        Resume copy = resume.clone();
        System.out.println(copy.getAddress() == resume.getAddress());
        copy.setName("Tom");
        copy.setPhone("18954120213");
        System.out.println(copy.toString());
    }

    /**
     * 浅拷贝
     * student---{"name":"Lucy","age":21,"nickNames":["lg","lk"],"bookList":[{"name":"java","price":23.5},{"name":"c++","price":33.5}]}
     * shallow---{"name":"Jack","age":21,"nickNames":["lg","lk","sk2"],"bookList":[{"name":"java","price":23.5},{"name":"c++","price":33.5},{"name":"python","price":11.5}]}
     * student---{"name":"Lucy","age":21,"nickNames":["lg","lk","sk2"],"bookList":[{"name":"java","price":23.5},{"name":"c++","price":33.5},{"name":"python","price":11.5}]}
     */
    public static void testShallowCopy() {
        Student student = new Student();
        student.setAge(21);
        student.setName("Lucy");
        //Arrays.asList("lg","lk") 这样的list是不可变的,java.lang.UnsupportedOperationException
        //student.setNickNames(Arrays.asList("lg","lk"));
        List<String> nickNameList = new ArrayList<>();
        nickNameList.add("lg");
        nickNameList.add("lk");
        student.setNickNames(nickNameList);
        List<Book> bookList = new ArrayList<>();
        Book b1 = new Book("java", 23.5d);
        Book b2 = new Book("c++", 33.5d);
        bookList.add(b1);
        bookList.add(b2);
        student.setBookList(bookList);
        System.out.println("student---" + GsonUtil.toJson(student));

        Student shallow = student.clone();
        shallow.setName("Jack");
        shallow.getNickNames().add("sk2");
        shallow.getBookList().add(new Book("python", 11.5d));
        System.out.println("shallow---" + GsonUtil.toJson(shallow));
        System.out.println("student---" + GsonUtil.toJson(student));
    }

    /**
     * 深拷贝 手动clone
     * student---{"name":"Lucy","age":21,"nickNames":["lg","lk"],"bookList":[{"name":"java","price":23.5},{"name":"c++","price":33.5}],"book":{"name":"Unity3","price":43.9}}
     * shallow---{"name":"Jack","age":21,"nickNames":["lg","lk","sk2"],"bookList":[{"name":"java","price":23.5},{"name":"c++","price":33.5},{"name":"python","price":11.5}],"book":{"name":"Unity4","price":43.9}}
     * student---{"name":"Lucy","age":21,"nickNames":["lg","lk"],"bookList":[{"name":"java","price":23.5},{"name":"c++","price":33.5}],"book":{"name":"Unity3","price":43.9}}
     */
    public static void testDeepClone() {
        Student1 student = new Student1();
        student.setAge(21);
        student.setName("Lucy");
        //Arrays.asList("lg","lk") 这样的list是不可变的,java.lang.UnsupportedOperationException
        //student.setNickNames(Arrays.asList("lg","lk"));
        List<String> nickNameList = new ArrayList<>();
        nickNameList.add("lg");
        nickNameList.add("lk");
        student.setNickNames(nickNameList);
        List<Book> bookList = new ArrayList<>();
        Book b1 = new Book("java", 23.5d);
        Book b2 = new Book("c++", 33.5d);
        bookList.add(b1);
        bookList.add(b2);
        student.setBookList(bookList);
        student.setBook(new Book("Unity3", 43.9d));
        System.out.println("student---" + GsonUtil.toJson(student));

        Student1 shallow = student.clone();
        shallow.setName("Jack");
        shallow.getNickNames().add("sk2");
        shallow.getBookList().add(new Book("python", 11.5d));
        shallow.getBook().setName("Unity4");
        System.out.println("shallow---" + GsonUtil.toJson(shallow));
        System.out.println("student---" + GsonUtil.toJson(student));
    }

    /**
     * 二进制流重建对象，只需要相关类实现Serializable接口，可序列化
     * 效果跟 深拷贝 手动clone 一样
     */
    public static void testStreamDeepClone() {
        Student2 student = new Student2();
        student.setAge(21);
        student.setName("Lucy");
        //Arrays.asList("lg","lk") 这样的list是不可变的,java.lang.UnsupportedOperationException
        //student.setNickNames(Arrays.asList("lg","lk"));
        List<String> nickNameList = new ArrayList<>();
        nickNameList.add("lg");
        nickNameList.add("lk");
        student.setNickNames(nickNameList);
        List<Book> bookList = new ArrayList<>();
        Book b1 = new Book("java", 23.5d);
        Book b2 = new Book("c++", 33.5d);
        bookList.add(b1);
        bookList.add(b2);
        student.setBookList(bookList);
        student.setBook(new Book("Unity3", 43.9d));
        System.out.println("student---" + GsonUtil.toJson(student));

        Student2 shallow = student.deepClone();
        shallow.setName("Jack");
        shallow.getNickNames().add("sk2");
        shallow.getBookList().add(new Book("python", 11.5d));
        shallow.getBook().setName("Unity4");
        System.out.println("shallow---" + GsonUtil.toJson(shallow));
        System.out.println("student---" + GsonUtil.toJson(student));
    }
}
