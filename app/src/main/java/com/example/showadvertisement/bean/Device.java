package com.example.showadvertisement.bean;

import android.os.Build;

public class Device {

    private final String manufacturer;
    private final String model;
    private final String version;
    private String type;

    public Device() {
        this.manufacturer = Build.MANUFACTURER;
        this.model = Build.MODEL;
        this.version = Build.VERSION.RELEASE;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getVersion() {
        return version;
    }
}
