package me.ellie.utils.library;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * App 相关工具类
 */
public class AppUtil {

    private AppUtil() {
    }

    /**
     * 获取 App 名称
     * @param context 上下文
     * @return App 名
     */
    public static String getAppName(Context context) {
        return getAppName(context, context == null ? null : context.getPackageName());
    }

    /**
     * 获取 App 名称
     * @param context 上下文
     * @param packageName 包名
     * @return App 名
     */
    public static String getAppName(Context context, String packageName) {
        if (context == null || TextUtils.isEmpty(packageName)) {
            return null;
        }
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取 App Version Name
     * @param context 上下文
     * @return Version Name
     */
    public static String getAppVersionName(Context context) {
        return getAppVersionName(context, context == null ? null : context.getPackageName());
    }

    /**
     * 获取 App Version Name
     * @param context 上下文
     * @param packageName 包名
     * @return Version Name
     */
    public static String getAppVersionName(Context context, String packageName) {
        if (context == null || TextUtils.isEmpty(packageName)) {
            return null;
        }
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取 App Version Code
     * @param context 上下文
     * @return Version Code
     */
    public static int getAppVersionCode(Context context) {
        return getAppVersionCode(context, context == null ? null : context.getPackageName());
    }

    /**
     * 获取 App Version Code
     * @param context 上下文
     * @param packageName 包名
     * @return Version Code
     */
    public static int getAppVersionCode(Context context, String packageName) {
        if (context == null || TextUtils.isEmpty(packageName)) {
            return -1;
        }
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? -1 : pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
