package com.xyz.jdk8characters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1","b");
//        List<String> b = new ArrayList<>();
//        list.addAll(b);
//        list.forEach(System.out::println);
//        System.out.println("Hello World!");
//        send(4,5);
        test();

    }

    public static void send(int a,int b,String...c){
        String[] k = c;
        System.out.println(k);
    }

    public static void test(){
        Pattern pattern = Pattern.compile("^\\[[\\*]*\\]$");
        Matcher m = pattern.matcher("[*****]");
        System.out.println(m.matches());
    }
}
