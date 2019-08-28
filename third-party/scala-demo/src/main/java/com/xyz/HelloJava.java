package com.xyz;

/**
 * Created by hzhsg on 2019/3/1.
 */
public class HelloJava {

    public static void main(String[] args) {
        HelloScala helloScala = new HelloScala();
        helloScala.say("Tim");

        String url = "https://oixlihnn9.qnssl.com/1490753971782750.jpg";
        System.out.println(url.substring(url.lastIndexOf("/")+1));
    }

    public void say(){
        System.out.println("hahahahhah===");
    }
}
