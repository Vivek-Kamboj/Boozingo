package com.example.pulkit.boozingo.helper;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Permission {
    public static final int RequestPermissionCode = 1;
    Activity activity;

    public Permission(Activity activity) {
        this.activity = activity;
    }

    public void requestPermission() {

        ActivityCompat.requestPermissions(activity, new String[]
                {
                        ACCESS_COARSE_LOCATION,
                        ACCESS_FINE_LOCATION,
                }, RequestPermissionCode);

    }



    public boolean checkPermission() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(activity, ACCESS_COARSE_LOCATION);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED;
    }
}
