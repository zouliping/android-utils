package me.ellie.utils.library;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Toast 工具类
 */
public class ToastUtil {

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    /**
     * 显示短 Toast
     *
     * @param context 上下文
     * @param text 显示内容
     */
    public static void showShortToast(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(context, text, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 显示短 Toast
     * @param context 上下文
     * @param resId 显示内容 id
     */
    public static void showShortToast(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(context, resId, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 显示长 Toast
     *
     * @param context 上下文
     * @param text 显示内容
     */
    public static void showLongToast(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(context, text, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 显示长 Toast
     * @param context 上下文
     * @param resId 显示内容 id
     */
    public static void showLongToast(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(context, resId, Toast.LENGTH_LONG);
            }
        });
    }

    private static void showToast(Context context, CharSequence text, int duration) {
        if (context != null && !TextUtils.isEmpty(text)) {
            Toast.makeText(context, text, duration).show();
        }
    }

    private static void showToast(Context context, int resId, int duration) {
        if (context != null && resId != 0) {
            Toast.makeText(context, resId, duration).show();
        }
    }

}
