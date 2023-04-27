package com.example.showadvertisement.database;

import android.provider.MediaStore;

import java.io.File;

public class Advertise {
    private String adId;

    private int type;//0,1,2  图片，视频

    private File file;

    public Advertise(String adId) {
        this.adId = adId;
    }
}
