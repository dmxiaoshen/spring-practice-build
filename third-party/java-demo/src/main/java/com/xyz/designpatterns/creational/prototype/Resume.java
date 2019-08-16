package com.xyz.designpatterns.creational.prototype;

/**
 * Created by hzhsg on 2018/5/3.
 * 原型模式适合在什么场景使用？一是类初始化需要消化非常多的资源，这个资源包括数据、硬件资源等；
 * 二是通过 new 产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式；
 * 三是一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时，
 * 可以考虑使用原型模式拷贝多个对象供调用者使用。
 * 在实际项目中，原型模式很少单独出现，一般是和工厂方法模式一起出现，
 * 通过 clone的方法创建一个对象，然后由工厂方法提供给调用者。
 * 原型模式先产生出一个包含大量共有信息的类，然后可以拷贝出副本，修正细节信息，建立了一个完整的个性对象。
 */
public class Resume implements Cloneable{

    private String name;
    private String address;
    private String phone;
    private int age;

    public Resume() {
        System.out.println("Resume的构造函数");
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    protected Resume clone(){
        Resume resume = null;
        try {
            resume = (Resume) super.clone();
        }catch (CloneNotSupportedException e){
            throw new RuntimeException(e);
        }
        return resume;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}
