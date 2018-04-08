package com.example.pulkit.boozingo.splash_screen;

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

import com.example.pulkit.boozingo.helper.LocationHelper;
import com.example.pulkit.boozingo.helper.ConnectionDetector;
import com.example.pulkit.boozingo.helper.Permission;
import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.Session;
import com.example.pulkit.boozingo.disclaimer.disclaimer;
import com.example.pulkit.boozingo.helper.SnackBarClass;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.OnMapReadyCallback;

import static com.example.pulkit.boozingo.helper.LocationHelper.REQUEST_CHECK_SETTINGS;
import static com.example.pulkit.boozingo.helper.LocationHelper.status;
import static com.example.pulkit.boozingo.helper.Permission.RequestPermissionCode;

public class SplashScreen extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks{

    private Snackbar snackbar;
    RelativeLayout layout;
    Session session;
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

        session = new Session(getApplicationContext());
        permission = new Permission(this);
        locationHelper = new LocationHelper(this);
        connectionDetector = new ConnectionDetector(this);

        layout = (RelativeLayout) findViewById(R.id.layout);
        _status = connectionDetector.getConnectivityStatusString(this);

        if (permission.checkPermission()) {
            setSnackbarMessage(_status, false);
        } else {
            permission.requestPermission();
        }


    }

    private void setSnackbarMessage(String status, boolean showBar) {

        String internetStatus = "No Internet. Check Connection";
        if (!status.equalsIgnoreCase("Wifi enabled") && !status.equalsIgnoreCase("Mobile data enabled")
                && session.isfirst()) {
            snackbar = Snackbar
                    .make(layout, internetStatus, Snackbar.LENGTH_INDEFINITE)
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
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);

            snackbar.show();
        } else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(SplashScreen.this, disclaimer.class));
                    finish();
                }
            }, DELAY_TIME);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean CoarseLocation = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean FineLocaion = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (CoarseLocation && FineLocaion) {

                        Toast.makeText(SplashScreen.this, "Permission Granted", Toast.LENGTH_LONG).show();
                        setSnackbarMessage(_status, false);

                    } else {

                        // permission was not granted
                        if (ActivityCompat.shouldShowRequestPermissionRationale(SplashScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                            permission.requestPermission();
                        } else {
                            Toast.makeText(this, "Permissions are necessary. Allow them from app settings.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
    }


    @Override
    public void onConnected(Bundle arg0) {
        // Once connected with google api, get the location
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


}
