package me.ellie.utils;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import me.ellie.utils.library.ClipboardUtil;

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
                Toast.makeText(this, "copied", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_get_btn:
                Toast.makeText(this, ClipboardUtil.getText(this), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
