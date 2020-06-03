package com.ytfu.yuntaifawu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/18
*
*  @Des 日期时间工具类
*
*/
public class DateUtil {

    /**
     * 时间戳转换日期格式字符串
     *
     * @param time ms时间戳
     */
    public static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(new Date(time));
    }

    /**
     * 日期格式字符串转换时间戳
     *
     * @return ms时间戳
     */
    public static long date2TimeStamp(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.parse(date).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取系统时间戳
     *
     * @return ms时间戳
     */
    public static long getCurTimeLong() {
        return System.currentTimeMillis();
    }

    /**
     * 获取系统日期
     *
     * @return format格式字符串
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        return simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取几天前的时间 并转换为相对应的格式
     */
    public static String agoTime2String(int day, String format) {
        SimpleDateFormat formats = new SimpleDateFormat(format, Locale.CHINA);
        long timeMillis = System.currentTimeMillis();
        long time = timeMillis - (long) day * 24 * 60 * 60 * 1000;
        return formats.format(new Date(time));
    }

    /**
     * 获取几天后的时间 并转换为相对应的格式
     */
    public static String afterTime2String(int day, String format) {
        SimpleDateFormat formats = new SimpleDateFormat(format, Locale.CHINA);
        long timeMillis = System.currentTimeMillis();
        long time = timeMillis + (long) day * 24 * 60 * 60 * 1000;
        return formats.format(new Date(time));
    }

    /**
     * 两个时间相差距离 ?天?小时?分?秒
     *
     * @param startDate 时间参数1格式：1990-01-01 12:00:00
     * @param endDate   时间参数2格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天,时,分,秒}
     */
    public static long[] getGapCount(String startDate, String endDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;

        try {
            one = format.parse(startDate);
            two = format.parse(endDate);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new long[]{day, hour, min, sec};
    }

}
