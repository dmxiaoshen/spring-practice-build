package com.xyz.designpatterns.behavior.iterator;

/**
 * Created by hzhsg on 2018/5/7.
 */
public class Employee {

    private String name;
    private String post;

    public Employee(String name, String post) {
        this.name = name;
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", post='" + post + '\'' +
                '}';
    }
}
