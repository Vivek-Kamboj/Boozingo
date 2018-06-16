package com.boozingo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.boozingo.helper.DBHelper;
import com.boozingo.helper.ImageUtils;
import com.boozingo.helper.SnackBarClass;
import com.boozingo.helper.TypefaceUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class Boozingo extends Application implements SnackBarClass.SnackbarMessage{

    private static Context appContext;
    public static Session session;
    public static RequestQueue requestQueue;
    public static DBHelper dbHelper;

    // for snack bar
    public static SnackBarClass snackBarClass;
    public static Snackbar snackbar;

    private boolean internetConnected = true;
    public static String internetStatus = "";

    public static String TAG = "TAG";
    public static String URL = "http://52.66.121.98/";


    public static String share_message = "Boozingo- Go with Boozingo- Your Personal Hangout Buddy!!\n" +
            "Boozingo is all about Booz & Boozing!!\nhttps://play.google.com/store/apps/details?id=com.boozingo";

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        dbHelper = new DBHelper(getApplicationContext());
        session = new Session(getApplicationContext());
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        snackBarClass = new SnackBarClass((Activity) getApplicationContext());
        snackBarClass.readySnackbarMessage((SnackBarClass.SnackbarMessage) getApplicationContext());

        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Medium.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf

    }

    public static Context getAppContext() {
        return appContext;
    }

    public static void showError(VolleyError error, Context context) {
        if (error.networkResponse == null)
            Toast.makeText(context, "Check Connection or token expired. Clear app data and restart app.", Toast.LENGTH_SHORT).show();

        else {
            String json = new String(error.networkResponse.data);
            JSONObject jsonObject = null;
            String message = null;
            try {
                jsonObject = new JSONObject(json);
                message = jsonObject.getString("message");
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    // for database images
    public static void saveImageInDB(Bitmap bitmap, String id) {

        dbHelper.open();
        byte[] inputData = ImageUtils.getImageBytes(bitmap);
        dbHelper.insertImage(inputData, id);
        dbHelper.close();

    }

    public static byte[] loadImageFromDB(String id) {

        byte[] bytes = null;
        try {
            dbHelper.open();
            bytes = dbHelper.retreiveImageFromDB(id);
            dbHelper.close();
        } catch (Exception e) {
            Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
            dbHelper.close();
        }

        return bytes;
    }


    @Override
    public void setSnackbarMessage(String status, boolean showBar,View layout ) {
        internetStatus = "";
        if (status.equalsIgnoreCase("Wifi enabled") || status.equalsIgnoreCase("Mobile data enabled")) {
            internetStatus = "Internet Connected";
        } else {
            internetStatus = "Lost Internet Connection";
        }
        snackbar = Snackbar
                .make(layout, internetStatus, Snackbar.LENGTH_LONG)
                .setAction("X", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                    }
                });

        // Changing message text color
        snackbar.setActionTextColor(Color.WHITE);
        // Changing action button text color
        View sbView = snackbar.getView();

        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        if (internetStatus.equalsIgnoreCase("Lost Internet Connection")) {
            if (internetConnected) {
                snackbar.show();
                internetConnected = false;
            }
        } else {
            if (!internetConnected) {
                internetConnected = true;
                snackbar.show();
            }
        }
    }

}
