package com.cathay.springbootswaggertest.utils;

import java.text.SimpleDateFormat;

/**
 * @author RogerLo
 * @date 2022/6/4
 */
public class MyDateUtil {

    /**
     * 傳入String回傳 java.sql.Date
     *
     * @param strDate 輸入 2022-11-11
     * */
    public static java.sql.Date strToSqlDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new java.sql.Date(d.getTime());
    }

}
