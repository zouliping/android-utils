package me.ellie.utils.library;

import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 编码工具类
 */
public class EncodeUtil {

    /**
     * url 编码
     *
     * @param s 要编码的字符串
     * @param charset 编码集
     * @return 编码后的字符串
     */
    public static String urlEncode(String s, String charset) {
        try {
            return URLEncoder.encode(s, charset);
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }

    /**
     * url 编码
     *
     * @param s 要编码的字符串
     * @return 编码后的字符串
     */
    public static String urlEncode(String s) {
        return urlEncode(s, "UTF-8");
    }

    /**
     * Base64 编码
     *
     * @param bytes 要编码的字节数组
     * @param flags 编码 flags
     * @return 编码后的字节数组
     */
    public static byte[] base64Encode(byte[] bytes, int flags) {
        if (bytes != null && bytes.length > 0) {
            return Base64.encode(bytes, flags);
        }
        return null;
    }

    /**
     * Base64 编码
     *
     * @param bytes 要编码的字节数组
     * @return 编码后的字节数组
     */
    public static byte[] base64Encode(byte[] bytes) {
        return base64Encode(bytes, Base64.NO_WRAP);
    }

    /**
     * Base64 编码
     *
     * @param s 要编码的字符串
     * @return 编码后的字节数组
     */
    public static byte[] base64Encode(String s) {
        if (!TextUtils.isEmpty(s)) {
            return base64Encode(s.getBytes());
        }
        return null;
    }

    /**
     * Base64 编码
     *
     * @param bytes 要编码的字节数组
     * @param flags 编码 flags
     * @return 编码后的字符串
     */
    public static String base64Encode2String(byte[] bytes, int flags) {
        if (bytes != null && bytes.length > 0) {
            return Base64.encodeToString(bytes, flags);
        }
        return null;
    }

    /**
     * Base64 编码
     *
     * @param bytes 要编码的字节数组
     * @return 编码后的字符串
     */
    public static String base64Encode2String(byte[] bytes) {
        return base64Encode2String(bytes, Base64.NO_WRAP);
    }

    /**
     * Base64 编码
     *
     * @param s 要编码的字符串
     * @return 编码后的字符串
     */
    public static String base64Encode2String(String s) {
        if (!TextUtils.isEmpty(s)) {
            return base64Encode2String(s.getBytes());
        }
        return null;
    }

}
