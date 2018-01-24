package com.example.pulkit.boozingo;

import android.app.Application;
import android.content.Context;

public class Boozingo extends Application {

    private static Context appContext;
    public static String url = "http://35.160.58.203";

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

    }
    public static Context getAppContext() {
        return appContext;
    }
}
