package me.ellie.utils.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

/**
 * 设备相关工具类
 */
public class DeviceUtil {

    private DeviceUtil() {
    }

    /**
     * 获取 Android Id
     *
     * @param context 上下文
     * @return Android Id
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidId(Context context) {
        if (context != null) {
            return android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        }
        return null;
    }

    /**
     * 获取 SDK 版本号
     *
     * @return SDK 版本号
     */
    public static int getAndroidSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备型号
     *
     * @return 设备型号
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取设备厂商
     *
     * @return 设备厂商
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取 Mac 地址
     * 需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}
     * Android 6.0 以上将返回 02:00:00:00:00:00
     *
     * @param context 上下文
     * @return Mac 地址
     */
    @SuppressLint("HardwareIds")
    public static String getMacAddressByWifi(Context context) {
        if (context != null) {
            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (wm != null) {
                WifiInfo wi = wm.getConnectionInfo();
                if (wi != null) {
                    return wi.getMacAddress();
                }
            }
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取 Mac 地址
     * 需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}
     *
     * @return Mac 地址
     */
    public static String getMacAddressByNetworkInterface() {
        try {
            List<NetworkInterface> niList = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface ni : niList) {
                if (!ni.getName().equalsIgnoreCase("wlan0")) {
                    continue;
                }

                byte[] macBytes = ni.getHardwareAddress();

                if (macBytes != null && macBytes.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : macBytes) {
                        sb.append(String.format("%02X:", b));
                    }

                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取 Mac 地址
     * 需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}
     * 和权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}
     *
     * @param context 上下文
     * @return Mac 地址
     */
    public static String getMacAddress(Context context) {
            String mac = getMacAddressByWifi(context);
        if ("02:00:00:00:00:00".equals(mac)) {
            mac = getMacAddressByNetworkInterface();
        }
        return mac;
    }

}
