package me.ellie.utils.library;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

/**
 * 权限工具类
 */
public class PermissionUtil {

    /**
     * 检查权限
     *
     * @param context 上下文
     * @param permission 权限
     * @return 是否授予权限
     */
    public static boolean checkPermission(Context context, String permission) {
        if (context != null && !TextUtils.isEmpty(permission)) {
            if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 被用户拒绝权限申请后弹的对话框
     *
     * @param activity 当前 Activity
     * @param msgId 显示的消息内容
     * @param titleId 显示的标题
     * @param settingId 显示的确定按钮文案
     * @param cancelId 显示的取消按钮文案
     */
    public static void showPermissionHintDialog(final Activity activity, int msgId, int titleId, int settingId, int cancelId) {
        new AlertDialog.Builder(activity).setTitle(titleId)
                .setMessage(msgId)
                .setPositiveButton(settingId, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + activity.getPackageName()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                })
                .setNegativeButton(cancelId, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    /**
     * 是否需要显示权限申请对话框
     *
     * @param activity 当前 Activity
     * @param msgId 显示的消息内容
     * @param titleId 显示的标题
     * @param settingId 显示的确定按钮文案
     * @param cancelId 显示的取消按钮文案
     * @param requestCode 请求码
     */
    public static void shouldShowPermissionHintDialog(Activity activity, String permission, int msgId, int titleId, int settingId, int cancelId, int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            showPermissionHintDialog(activity, msgId, titleId, settingId, cancelId);
        } else {
            requestPermission(activity, permission, requestCode);
        }
    }

    /**
     * 申请权限
     *
     * @param activity 当前 Activity
     * @param permission 权限
     * @param requestCode 请求码
     */
    public static void requestPermission(Activity activity, String permission, int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }

    /**
     * 申请权限
     *
     * @param fragment 当前 Fragment
     * @param permission 权限
     * @param requestCode 请求码
     */
    public static void requestPermission(Fragment fragment, String permission, int requestCode) {
        fragment.requestPermissions(new String[]{permission}, requestCode);
    }

}

