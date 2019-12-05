package com.ual.blog.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DataUtil
 * @Description TODO
 * @Author ual
 */
public abstract class DateUtil {
    private static final String PATTERN="yyyy-MM-dd HH:mm:ss";

    /**
     * Data转String
     */
    public static String formateToStr(Date date,String pattern){
        if(pattern==null||"".equals(pattern)){
            pattern=PATTERN;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        String result=df.format(date);
        return result;
    }

    /**
     * String转Date
     */
    public static Date parseToDate(String dateStr,String pattern){
        if(pattern==null||"".equals(pattern)){
            pattern=PATTERN;
        }
        DateFormat df=new SimpleDateFormat(pattern);
        try {
            return df.parse(dateStr);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
