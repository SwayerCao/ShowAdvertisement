package com.example.showadvertisement;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends FragmentActivity implements View.OnClickListener,View.OnFocusChangeListener{

//    private ItemSelectedListener itemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initUISelectable();

    }

    private void initUISelectable() {
//        TextView button = findViewById(R.id.btn);
//        TextView button1 = findViewById(R.id.btn1);
//        TextView button2 = findViewById(R.id.btn3);
//        TextView button3 = findViewById(R.id.btn4);
//        button.setOnClickListener(this);
//        button1.setOnClickListener(this);
//        button2.setOnClickListener(this);
//        button3.setOnClickListener(this);
//        LinearLayout linearLayout = findViewById(R.id.ll_1);
//        linearLayout.setOnFocusChangeListener(this);
//        button.setOnFocusChangeListener(this);
//        button1.setOnFocusChangeListener(this);
//        button2.setOnFocusChangeListener(this);
//        button3.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_down:
                Toast.makeText(this, "按钮", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_SETTINGS));
                break;

            case R.id.btn_clear_idle:
                Toast.makeText(this, "按钮1", Toast.LENGTH_SHORT).show();
            break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            v.setBackground(this.getDrawable(R.color.selected_background));
        } else {
            v.setBackground(this.getDrawable(androidx.leanback.R.color.lb_tv_white));
        }
    }




}