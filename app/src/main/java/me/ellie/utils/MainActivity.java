package me.ellie.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.ellie.utils.library.AppUtil;
import me.ellie.utils.library.ClipboardUtil;
import me.ellie.utils.library.EmptyUtil;
import me.ellie.utils.library.EncodeUtil;
import me.ellie.utils.library.KeyboardUtil;
import me.ellie.utils.library.LogUtil;
import me.ellie.utils.library.PermissionUtil;
import me.ellie.utils.library.TimeUtil;
import me.ellie.utils.library.ToastUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView tvShowTime, tvEmpty, tvAppInfo;
    private EditText etContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_copy_btn).setOnClickListener(this);
        findViewById(R.id.main_get_btn).setOnClickListener(this);
        findViewById(R.id.main_get_time_btn).setOnClickListener(this);
        tvShowTime = (TextView) findViewById(R.id.main_show_time_tv);
        findViewById(R.id.main_empty_btn).setOnClickListener(this);
        tvEmpty = (TextView) findViewById(R.id.main_show_empty_tv);
        etContent = (EditText) findViewById(R.id.main_content_et);
        findViewById(R.id.main_show_keyboard_btn).setOnClickListener(this);
        findViewById(R.id.main_hide_keyboard_btn).setOnClickListener(this);
        findViewById(R.id.main_url_encode_btn).setOnClickListener(this);
        findViewById(R.id.main_base64_encode_btn).setOnClickListener(this);
        tvAppInfo = (TextView) findViewById(R.id.main_app_info_tv);
        findViewById(R.id.main_app_info_btn).setOnClickListener(this);
        findViewById(R.id.main_permission_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_copy_btn:
                ClipboardUtil.copyText(this, "copy me");
                ToastUtil.showShortToast(this, "copied");
                ToastUtil.showShortToast(this, R.string.app_name);
                LogUtil.i(MainActivity.class.getSimpleName(), "copied");
                break;
            case R.id.main_get_btn:
                ToastUtil.showLongToast(this, ClipboardUtil.getText(this));
                LogUtil.d(MainActivity.class.getSimpleName(), ClipboardUtil.getText(this).toString());
                break;
            case R.id.main_get_time_btn:
                long time = System.currentTimeMillis();
                tvShowTime.setText("current time:" + TimeUtil.millis2Str(time) + "\n"
                        + "current date: " + TimeUtil.millis2Str(time, "yyyy-MM-dd") + "\n"
                        + "string to millis: " + TimeUtil.string2Millis("2016-12-12", "yyyy-MM-dd") + "\n"
                        + "string to millis: " + TimeUtil.string2Millis("2016-12-12 15:15:15") + "\n"
                        + "show date: " + TimeUtil.getShowDate(time) + "\n"
                        + "is same day: " + TimeUtil.isSameDay(time, time + 3000) + "\n"
                        + "get days interval: " + TimeUtil.getDaysInterval("20161210", "20161112", "yyyyMMdd") + "\n"
                        + "get days interval: " + TimeUtil.getDaysInterval("2016-12-10 02:10:10", "2016-11-12 18:10:20") + "\n");
                break;
            case R.id.main_empty_btn:
                List<String> strList = new ArrayList<>();
                SparseArray<String> strSA = new SparseArray<>();
                tvEmpty.setText("strList is empty " + EmptyUtil.isEmpty(strList)
                        + "\nstrSA is empty " + EmptyUtil.isEmpty(strSA)
                        + "\n strList is list empty " + EmptyUtil.listIsEmpty(strList));
                break;
            case R.id.main_show_keyboard_btn:
                KeyboardUtil.showKeyboard(this, etContent);
                break;
            case R.id.main_hide_keyboard_btn:
                KeyboardUtil.hideKeyboard(this);
                break;
            case R.id.main_url_encode_btn:
                LogUtil.d(TAG, "url encode test:" + EncodeUtil.urlEncode("test") + "\n"
                        + "url encode 工具类:" + EncodeUtil.urlEncode("工具类"));
                break;
            case R.id.main_base64_encode_btn:
                LogUtil.d(TAG, "base64 encode test:" + EncodeUtil.base64Encode2String("test") + "\n"
                        + "base64 encode 工具类:" + Arrays.toString(EncodeUtil.base64Encode("工具类".getBytes())));
                break;
            case R.id.main_app_info_btn:
                tvAppInfo.setText("app name:" + AppUtil.getAppName(this) + "\n"
                        + "app version name:" + AppUtil.getAppVersionName(this) + "\n"
                        + "app version code:" + AppUtil.getAppVersionCode(this));
                break;
            case R.id.main_permission_btn:
                if (PermissionUtil.checkPermission(this, Manifest.permission.CAMERA)) {
                    ToastUtil.showShortToast(this, "camera permission already granted");
                } else {
                    PermissionUtil.requestPermission(this, Manifest.permission.CAMERA, 10001);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10001 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            ToastUtil.showShortToast(this, "camera permission granted");
        } else {
            ToastUtil.showShortToast(this, "camera permission denied");
        }
    }
}
