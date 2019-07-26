package com.xiaouchina.common.utils.test;

import com.xiaouchina.common.utils.DateUtils;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void getDate() {
        Date today = DateUtils.cutOff(new Date());
        Date w = DateUtils.getDate(today,1, Calendar.WEEK_OF_YEAR);
        Date m = DateUtils.getDate(today,1,Calendar.MONTH);
        Date y = DateUtils.getDate(today,1,Calendar.YEAR);
        System.out.println(DateUtils.format(w,"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils.format(m,"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils.format(y,"yyyy-MM-dd HH:mm:ss"));

    }
}