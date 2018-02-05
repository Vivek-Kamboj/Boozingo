package com.example.pulkit.boozingo;

import android.app.Application;
import android.content.Context;

import com.example.pulkit.boozingo.helper.TypefaceUtil;

public class Boozingo extends Application {

    private static Context appContext;
    public static String url = "http://35.160.58.203";

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Medium.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf

    }
    public static Context getAppContext() {
        return appContext;
    }
}
