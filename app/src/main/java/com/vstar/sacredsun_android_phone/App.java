package com.vstar.sacredsun_android_phone;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by tanghuailong on 2017/2/9.
 */

public class App extends Application{

    private static App instant;

    public App() {
        instant = this;
    }
    public static App getInstance() {
        return instant;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

}
