package com.example.showadvertisement;

import android.app.Application;
import com.example.showadvertisement.utils.SdkManager;

public class App extends Application  {

    @Override
    public void onCreate() {
        super.onCreate();
        SdkManager.getInStance().initApp(this);
    }

}
