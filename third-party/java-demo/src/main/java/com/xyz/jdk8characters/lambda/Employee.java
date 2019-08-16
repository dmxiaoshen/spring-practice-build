package com.xyz.jdk8characters.lambda;

/**
 * Created by hzhsg on 2018/7/13.
 */
public class Employee {

    private String name;
    private String address;
    private double salary;
    private int age;

    public Employee(String name, String address, double salary, int age) {
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
