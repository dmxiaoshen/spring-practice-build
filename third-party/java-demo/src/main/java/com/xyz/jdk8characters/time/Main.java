package com.xyz.jdk8characters.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by hzhsg on 2018/7/17.
 */
public class Main {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        Instant now = Instant.now();
        Instant now2 = Instant.ofEpochMilli(new Date().getTime());
        System.out.println(now);
        System.out.println(now2);

        LocalDate lnow = LocalDate.now();
        System.out.println(lnow);
        LocalDate lnow2 = LocalDate.of(2018,7,23).minusDays(2);
        System.out.println(lnow2);

        LocalTime lt = LocalTime.now();
        System.out.println(lt);
        LocalTime lt2 = LocalTime.of(18,12,32).minusHours(1);
        System.out.println(lt2);
        LocalTime lt3 = LocalTime.ofSecondOfDay(4536);
        System.out.println(lt3);

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        LocalDateTime ldt2 = LocalDateTime.of(2018,7,17,23,32,45);
        System.out.println(ldt2);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2018,7,17,16,0,0),ZoneId.of("+08"));
        System.out.println(zonedDateTime);
        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt);

        String str = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        System.out.println("str-"+str);
        String str1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.of(LocalDate.now(),LocalTime.now()));
        System.out.println("str1-"+str1);
        String str2 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
        System.out.println("str2-"+str2);

        LocalDateTime localDateTime = LocalDateTime.parse("2018-07-17 18:37:56",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(localDateTime);
    }
}
