package com.example.pulkit.boozingo;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class MarshmallowPermissions {

    public static final int LOCSERV_REQUEST_CODE = 5;
    public static final int CALL = 4;
    Activity activity;

    public MarshmallowPermissions(Activity activity) {
        this.activity = activity;
    }

    public boolean checkPermissionForFineLocation() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    public boolean checkPermissionForCoarseLocation() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    public boolean checkPermissionForCall() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);
        return result == PackageManager.PERMISSION_GRANTED;
    }


    public void requestPermissionForFineLocation() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCSERV_REQUEST_CODE);

    }

    public void requestPermissionForCall() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, CALL);

    }

    public void requestPermissionForCoarseLocation() {
      /*  if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Toast.makeText(activity, "Please allow us to use locations.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCSERV_REQUEST_CODE);
        }*/

        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCSERV_REQUEST_CODE);

    }



}