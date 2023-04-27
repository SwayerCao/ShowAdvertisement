package com.example.showadvertisement.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.example.showadvertisement.database.MyFolder;

import java.io.File;

public class SdkManager {

    private static volatile SdkManager sdkManager;
    private Context context;
    public Timer timer;

    public static SdkManager getInStance() {
        if (sdkManager == null) {
            synchronized (SdkManager.class) {
                if (sdkManager == null) {
                    sdkManager = new SdkManager();
                }
            }
        }
        return sdkManager;
    }

    private SdkManager() {}

    public void initApp(Context context) {
        this.context = context;
        initSdCardDir(context);
        initAdvertiseDB();
        startLoop();
        getClass().getSimpleName();
        MyFolder myFolder = MyFolder.getMyFolderInstance();

        myFolder.storageFile(myFolder.copyFrom(),"today");

        // TODO: 2023/4/23 OKHttp请求
        //OkHttp请求 之后再
        UsbSetting.DisallowUseUsb();
    }

    private void startLoop() {
        timer = new Timer();
        timer.work("第一个循环", new Timer.TimerHandler() {
            @Override
            public void handle(Timer.Data data) {
                handleTest(data);
            }
        },10);
    }

    private void handleTest(Timer.Data data) {
        Toast.makeText(context,data.tag,Toast.LENGTH_SHORT).show();
        timer.work("第一个循环", new Timer.TimerHandler() {
            @Override
            public void handle(Timer.Data data) {
                handleTest(data);
            }
        },10);
    }

    private void initSdCardDir(Context context) {

        File file = new File(Environment.getExternalStorageDirectory()+ "/advertise/");
        if (!file.exists() && Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            file.mkdirs();
//            Toast.makeText(context, "文件夹创建", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "文件夹已存在", Toast.LENGTH_SHORT).show();
        }
        downloadImageFiles(context);
    }

    private void downloadImageFiles(Context context) {
        File imageFile = new File("src/imagefile");

        if (imageFile.isDirectory() && imageFile.exists()) {
            Toast.makeText(context, "文件夹已存在", Toast.LENGTH_SHORT).show();
        }
    }


    private void initAdvertiseDB() {

    }
}
