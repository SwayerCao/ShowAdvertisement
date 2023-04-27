package com.example.showadvertisement.setting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.showadvertisement.MainActivity;

public class AutoRun extends BroadcastReceiver {

    String TAG = "AutoRun";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent intentStart = new Intent();
            intentStart.setClass(context, MainActivity.class);// 开机后指定要执行程序的界面文件
            intentStart.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentStart);
        }
    }
}
