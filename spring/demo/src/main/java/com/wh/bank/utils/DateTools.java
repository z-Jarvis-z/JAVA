package com.wh.bank.utils;

import java.util.Calendar;
import java.util.Date;

public class DateTools {

    public static Date getYearTime(int num)
    {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, num);
        return calendar.getTime();
    }
}