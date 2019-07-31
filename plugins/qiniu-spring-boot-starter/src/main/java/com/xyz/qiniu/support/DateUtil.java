package com.xyz.qiniu.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 字符串转日期
     * @param dateStr
     * @param pattren
     * @return
     */
    public static Date dateFormat(String dateStr, String pattren) {
        SimpleDateFormat sdt = new SimpleDateFormat(pattren);
        try {
            return sdt.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("dateFormat error",e);
        }
    }
}
