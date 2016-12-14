package me.ellie.utils.library;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

/**
 * 剪切板工具类
 */
public class ClipboardUtil {

    /**
     * 复制文本到剪切板
     *
     * @param context 上下文
     * @param text 文本
     */
    public static void copyText(Context context, CharSequence text) {
        if (context != null && !TextUtils.isEmpty(text)) {
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(ClipData.newPlainText("text", text));
        }
    }

    /**
     * 获取剪切板文本
     *
     * @param context 上下文
     * @return 剪切板文本
     */
    public static CharSequence getText(Context context) {
        if (context != null) {
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData cp = cm.getPrimaryClip();
            if (cp != null && cp.getItemCount() > 0) {
                return cp.getItemAt(0).coerceToText(context);
            }
        }
        return null;
    }

}

