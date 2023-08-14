package com.ktc.ausiankou;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.ktc.ausiankou.Constants.INVALID_DATE_FORMAT;

public class Utils {
    public static java.sql.Date getDate(String strDate){
        try{
            return java.sql.Date.valueOf(strDate);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(INVALID_DATE_FORMAT);
        }
    }

    public static boolean dateTwoIsMoreThanDateOne(String date1, String date2){
        try{
            return getDate(date1).compareTo(getDate(date2)) >= 0;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(INVALID_DATE_FORMAT);
        }
    }

    public static boolean dateIsMoreThanToday(String date){
        try{
            return dateTwoIsMoreThanDateOne(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()), date);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(INVALID_DATE_FORMAT);
        }
    }
}
