package me.ellie.utils.library;

import android.util.Log;

/**
 * 日志相关工具类
 */
public class LogUtil {

    private static boolean sLogMode = true;

    public static void setLogMode(boolean sLogMode) {
        LogUtil.sLogMode = sLogMode;
    }

    public static boolean checkLogMode() {
        return sLogMode;
    }

    private LogUtil() {
    }

    /**
     * 打印 VERBOSE 日志
     *
     * @param tag 标识
     * @param msg 消息
     */
    public static void v(String tag, String msg) {
        log(tag, msg, Log.VERBOSE);
    }

    /**
     * 打印 DEBUG 日志
     *
     * @param tag 标识
     * @param msg 消息
     */
    public static void d(String tag, String msg) {
        log(tag, msg, Log.DEBUG);
    }

    /**
     * 打印 INFO 日志
     *
     * @param tag 标识
     * @param msg 消息
     */
    public static void i(String tag, String msg) {
        log(tag, msg, Log.INFO);
    }

    /**
     * 打印 WARN 日志
     *
     * @param tag 标识
     * @param msg 消息
     */
    public static void w(String tag, String msg) {
        log(tag, msg, Log.WARN);
    }

    /**
     * 打印 ERROR 日志
     *
     * @param tag 标识
     * @param msg 消息
     */
    public static void e(String tag, String msg) {
        log(tag, msg, Log.ERROR);
    }

    /**
     * 打印日志
     *
     * @param tag 消息的标识
     * @param msg 消息
     * @param priority 日志类型
     */
    private static void log(String tag, String msg, int priority) {
        if (sLogMode) {
            switch (priority) {
                case Log.VERBOSE:
                    Log.v(tag, msg);
                    break;
                case Log.DEBUG:
                    Log.d(tag, msg);
                    break;
                case Log.INFO:
                    Log.i(tag, msg);
                    break;
                case Log.WARN:
                    Log.w(tag, msg);
                    break;
                case Log.ERROR:
                    Log.e(tag, msg);
                    break;
                default:
                    Log.v(tag, msg);
                    break;
            }
        }
    }
}
