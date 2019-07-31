package com.xyz.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date cutOff(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }

    public static Date getDate(Date date,int dis,int type){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(type,dis);
        return cal.getTime();
    }

    public static Date addDate(Date date,int dis){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR,dis);
        return c.getTime();
    }

    public static String format(Date date,String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 两个日期之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetweenTwoDate(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static void main(String[] args) {
//        Date d = cutOff(new Date());
//        System.out.println(format(d,"yyyy-MM-dd HH:mm:ss"));
//        System.out.println(format(addDate(d,8),"yyyy-MM-dd HH:mm:ss"));
        Date a = cutOff(new Date());
        Date b = addDate(a,-7);
        Date c = a;
        System.out.println(daysBetweenTwoDate(a,c));
        System.out.println(format(b,"yyyy-MM-dd HH:mm:ss"));
    }
}
