package com.example.pulkit.boozingo.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.widget.Toast;

public class GPSClass {
    Activity activity;
    LocationManager locationManager;
    boolean GpsStatus;
    Intent intent;
    final public static int GPS_CODE = 13;

    public GPSClass(Activity activity) {
        this.activity = activity;
    }

    public void GPSenable() {
        GPSStatus();
        if (!GpsStatus) {
            Toast.makeText(activity, "Please turn on the location.", Toast.LENGTH_SHORT).show();
            intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            activity.startActivityForResult(intent,GPS_CODE);

  //          GPSenable();
        }
    }



    public void GPSStatus() {
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

}
