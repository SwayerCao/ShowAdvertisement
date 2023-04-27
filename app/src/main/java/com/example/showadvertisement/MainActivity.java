package com.example.showadvertisement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.example.showadvertisement.download.Downloader;
import com.example.showadvertisement.utils.SdkManager;
import com.example.showadvertisement.view.AdActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class MainActivity extends FragmentActivity {

    Button btn_ad,btn_setting;

    public static String TAG = "MainActivity.class";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_ad = findViewById(R.id.ad_enter);
        btn_setting = findViewById(R.id.setting_enter);
        btn_ad.setOnClickListener(v -> {

            Intent intent = new Intent(this, AdActivity.class);
            startActivity(intent);
        });
        btn_setting.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        });
        ArrayList<String> strings =  getDownList();
        if (strings.size() > 0) {
            download(strings);
        }
        timeToAd();

    }

    private void download(ArrayList<String> strings) {
        ArrayList<String> errorUrl = new ArrayList<>();

        for (String url:strings) {
            Downloader.getDownloader().download(url, new Downloader.OnDownloadListener() {
                @Override
                public void onDownLoadSuccess() {
                    Log.d(TAG, "onDownLoadSuccess: " + "下载成功");
                }

                @Override
                public void onDownloadFail(String failUrl) {
                    errorUrl.add(failUrl);
                    Log.d(TAG, "onDownloadFail: " + "下载失败");
                }

                @Override
                public void onDownloading(int progress) {
                    Log.d(TAG, "onDownloading: " + progress + "下载中");
                }
            });

        }
    }

    private ArrayList<String> getDownList() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("https://img0.baidu.com/it/u=2289446283,2987162055&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800.png");

        // TODO: 2023/4/23
        return strings;
    }


    private void timeToAd() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int countdown = 10;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btn_ad.setText(String.valueOf(countdown--));
                        Log.d(TAG, "run: " + countdown);
                        if (countdown == 0) {
                            cancel();
                            Intent intent = new Intent(MainActivity.this,AdActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);
    }

    @Override
    protected void onDestroy() {
        SdkManager.getInStance().timer.quit();
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}