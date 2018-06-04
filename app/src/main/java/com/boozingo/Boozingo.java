package com.boozingo;

import android.app.Application;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.boozingo.helper.TypefaceUtil;

public class Boozingo extends Application {

    private static Context appContext;
    public static String url = "http://52.66.121.98";
    public static String share_message = "Boozingo- Go with Boozingo- Your Personal Hangout Buddy!!\n" +
            "Boozingo is all about Booz & Boozing!!\nhttps://play.google.com/store/apps/details?id=com.boozingo";

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
