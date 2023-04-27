package com.example.showadvertisement.utils;

import android.util.Log;

import java.io.IOException;

public class UsbSetting {
    final private static String TAG = "UsbSetting";
    public static void AllowUseUsb() {    //允许使用USB
        Command.command("setprop persist.sys.usb.config mtp,adb");
    }
    public static void DisallowUseUsb() {   //禁止使用USB
        Command.command("setprop persist.sys.usb.config none");
    }

    static class Command {
        final private static String TAG = "Command";
        public static void command(String com) {
            try {
                Log.i(TAG, "Command : " + com);
                Runtime.getRuntime().exec(com);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}


