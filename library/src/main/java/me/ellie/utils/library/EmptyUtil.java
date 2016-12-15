package me.ellie.utils.library;

import android.os.Build;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 判空工具类
 */
public class EmptyUtil {

    /**
     * 是否为空
     * @param object 判断对象
     * @return 是否为空
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }

        if (object instanceof String && object.toString().length() == 0) {
            return true;
        }

        if (object.getClass().isArray() && Array.getLength(object) == 0) {
            return true;
        }

        if (object instanceof Collection && ((Collection) object).isEmpty()) {
            return true;
        }

        if (object instanceof Map && ((Map) object).isEmpty()) {
            return true;
        }

        if (object instanceof SparseArray && ((SparseArray) object).size() == 0) {
            return true;
        }

        if (object instanceof SparseIntArray && ((SparseIntArray) object).size() == 0) {
            return true;
        }

        if (object instanceof SparseBooleanArray && ((SparseBooleanArray) object).size() == 0) {
            return true;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (object instanceof SparseLongArray && ((SparseLongArray) object).size() == 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * list 是否为空
     * @param list 需判空的 list
     * @return 是否为空
     */
    public static boolean listIsEmpty(List list) {
        return list == null || list.size() == 0;
    }
}
