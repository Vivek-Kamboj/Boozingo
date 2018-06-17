package com.boozingo.splash_screen;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boozingo.cities.Cities;
import com.boozingo.helper.LocationHelper;
import com.boozingo.helper.ConnectionDetector;
import com.boozingo.helper.Permission;
import com.boozingo.R;
import com.boozingo.disclaimer.disclaimer;
import com.boozingo.helper.SnackBarClass;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.boozingo.Boozingo.snackBarClass;
import static com.boozingo.helper.LocationHelper.REQUEST_CHECK_SETTINGS;
import static com.boozingo.helper.LocationHelper.status;
import static com.boozingo.helper.Permission.RequestPermissionCode;

public class SplashScreen extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    RelativeLayout layout;
    String _status;
    Permission permission;
    final int DELAY_TIME = 1500;
    ConnectionDetector connectionDetector;
    LocationHelper locationHelper;
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //No title bar is set for the activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        permission = new Permission(this);
        locationHelper = new LocationHelper(this);
        connectionDetector = new ConnectionDetector(this);
        layout = findViewById(R.id.layout);

        permission.requestPermission();


    }


    @Override
    public void onConnected(Bundle arg0) {
        mLastLocation = locationHelper.getLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        locationHelper.connectApiClient();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case RESULT_OK:
                        Log.e("d", "d");
                        // All required changes were successfully made
                        mLastLocation = locationHelper.getLocation();
                        break;
                    case RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        try {
                            status.startResolutionForResult(this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        snackBarClass.registerInternetCheckReceiver(layout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (snackBarClass.broadcastReceiver.isOrderedBroadcast())
            unregisterReceiver(snackBarClass.broadcastReceiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean CoarseLocation = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean FineLocaion = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (!CoarseLocation || !FineLocaion) {
                        // permission was not granted
                        if (ActivityCompat.shouldShowRequestPermissionRationale(SplashScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                            permission.requestPermission();
                        } else {
                            Toast.makeText(this, "Permissions are necessary. Allow them from app settings.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        startActivity(new Intent(SplashScreen.this, Cities.class));
                        finish();
                    }
                }
                break;
        }
    }

}
