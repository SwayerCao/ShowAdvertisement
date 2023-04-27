package com.example.showadvertisement.download;

import android.content.res.Resources;
import android.os.Environment;

import androidx.annotation.NonNull;

import com.example.showadvertisement.database.MyFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Downloader {

    private static Downloader downloader;
    private final OkHttpClient okHttpClient;

    public static Downloader getDownloader() {
        if (downloader == null) {
            downloader = new Downloader();
        }
        MyFolder.getMyFolderInstance();
        return downloader;
    }

    private Downloader() {
        okHttpClient = new OkHttpClient();
    }

    public void download(String url,final OnDownloadListener listener) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listener.onDownloadFail(url);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //初始化文件输入输出流
                InputStream inputStream = null;
                byte[] bytes = new byte[5*5120];
                int len = 0;
                FileOutputStream fileOutputStream = null;
                String savePath =  MyFolder.getMyFolderInstance().getStoragePath();
                try {
                    //获取response字节流
                    inputStream = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(savePath,getNameFromUrl(url));
                    fileOutputStream = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = inputStream.read(bytes)) != -1) {
                        fileOutputStream.write(bytes,0,len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        listener.onDownloading(progress);
                    }
                    fileOutputStream.flush();

                    listener.onDownLoadSuccess();
                } catch (Exception e) {
                    listener.onDownloadFail(url);
                    e.printStackTrace();
                } finally {
                    try {
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private String isExistDir(String saveDir) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!file.mkdirs()) {
            file.createNewFile();
        }

        return file.getAbsolutePath();
    }

    public static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public interface OnDownloadListener {

        void onDownLoadSuccess();

        void onDownloadFail(String failUrl);

        void onDownloading(int progress);
    }

}
