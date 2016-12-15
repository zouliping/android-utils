package me.ellie.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.ellie.utils.library.ClipboardUtil;
import me.ellie.utils.library.EmptyUtil;
import me.ellie.utils.library.TimeUtil;
import me.ellie.utils.library.ToastUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvShowTime, tvEmpty;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_copy_btn:
                ClipboardUtil.copyText(this, "copy me");
                ToastUtil.showShortToast(this, "copied");
                ToastUtil.showShortToast(this, R.string.app_name);
                break;
            case R.id.main_get_btn:
                ToastUtil.showLongToast(this, ClipboardUtil.getText(this));
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
                + "get days interval: " + TimeUtil.getDaysInterval("2016-12-10 02:10:10", "2016-11-12 18:10:20") + "\n"
                );
                break;
            case R.id.main_empty_btn:
                List<String> strList = new ArrayList<>();
                SparseArray<String> strSA = new SparseArray<>();
                tvEmpty.setText("strList is empty " + EmptyUtil.isEmpty(strList)
                        + "\nstrSA is empty " + EmptyUtil.isEmpty(strSA)
                        + "\n strList is list empty " + EmptyUtil.listIsEmpty(strList));
                break;
        }
    }
}
