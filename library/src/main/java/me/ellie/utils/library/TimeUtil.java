package me.ellie.utils.library;

import android.text.TextUtils;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * 时间相关的工具类
 */
public class TimeUtil {

    private static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private TimeUtil() {
    }

    /**
     * 时间字符串转换为 Date 类型
     *
     * @param time 时间字符串
     * @param pattern 时间格式
     * @return Date
     */
    public static Date string2Date(String time, String pattern) {
        return new Date(string2Millis(time, pattern));
    }

    /**
     * 时间字符串转换为 Date 类型
     *
     * @param time 时间字符串
     * @return Date
     */
    public static Date string2Date(String time) {
        return string2Date(time, DEFAULT_TIME_PATTERN);
    }

    /**
     * 时间戳转换为时间字符串
     *
     * @param timeMillis 时间戳
     * @param pattern 时间格式
     * @return 时间字符串
     */
    public static String millis2Str(long timeMillis, String pattern) {
        if (timeMillis > 0 && !TextUtils.isEmpty(pattern)) {
            return new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date(timeMillis));
        }
        return null;
    }

    /**
     * 时间戳转换为时间字符串
     *
     * @param timeMillis 时间戳
     * @return 时间字符串
     */
    public static String millis2Str(long timeMillis) {
        return millis2Str(timeMillis, DEFAULT_TIME_PATTERN);
    }

    /**
     * 时间字符串转换为时间戳
     *
     * @param date 时间字符串
     * @param pattern 时间格式
     * @return 时间戳
     */
    public static long string2Millis(String date, String pattern) {
        try {
            if (!TextUtils.isEmpty(date) && !TextUtils.isEmpty(pattern)) {
                return new SimpleDateFormat(pattern, Locale.getDefault()).parse(date).getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 时间字符串转换为时间戳
     *
     * @param date 时间字符串
     * @return 时间戳
     */
    public static long string2Millis(String date) {
        return string2Millis(date, DEFAULT_TIME_PATTERN);
    }

    /**
     * 是否是今年
     *
     * @param time 时间戳
     * @return 是否是今年
     */
    public static boolean isThisYear(long time) {
        if (time <= 0) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        calendar.setTimeInMillis(System.currentTimeMillis());
        return year == calendar.get(Calendar.YEAR);
    }

    /**
     * 是否是同一天
     *
     * @param time1 时间戳1
     * @param time2 时间戳2
     * @return 是否是同一天
     */
    public static boolean isSameDay(long time1, long time2) {
        if (time1 <= 0 || time2 <= 0) {
            return false;
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTimeInMillis(time1);
        calendar2.setTimeInMillis(time2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取利于显示的时间字符串
     *
     * @param time 时间戳
     * @return 友好显示的时间字符串
     */
    public static String getShowDate(long time) {
        if (DateUtils.isToday(time)) {
            return millis2Str(time, "HH:mm");
        } else if (isThisYear(time)) {
            return millis2Str(time, "MM-dd");
        } else {
            return millis2Str(time, "yyyy-MM-dd");
        }
    }

    /**
     * 两个时间之间的间隔天数
     *
     * @param time1 时间字符串1
     * @param time2 时间字符串2
     * @param pattern 时间格式
     * @return 间隔天数
     */
    public static long getDaysInterval(String time1, String time2, String pattern) {
        Date date1 = string2Date(time1, pattern);
        Date date2 = string2Date(time2, pattern);
        return TimeUnit.DAYS.convert(date1.getTime() - date2.getTime(), TimeUnit.MILLISECONDS);
    }

    /**
     * 两个时间之间的间隔天数
     *
     * @param time1 时间字符串1
     * @param time2 时间字符串2
     * @return 间隔天数
     */
    public static long getDaysInterval(String time1, String time2) {
        return getDaysInterval(time1, time2, DEFAULT_TIME_PATTERN);
    }
}
