package me.ellie.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.ellie.utils.library.ClipboardUtil;
import me.ellie.utils.library.ToastUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_copy_btn).setOnClickListener(this);
        findViewById(R.id.main_get_btn).setOnClickListener(this);
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
        }
    }
}
