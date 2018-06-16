package com.boozingo.helper;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import static com.boozingo.Boozingo.snackBarClass;

public class SnackBarClass {
    public Snackbar snackbar;
    public boolean internetConnected = true;
    Context activity;
    View layout;
    ConnectionDetector connectionDetector;

    public SnackBarClass(Context activity) {
        this.activity = activity;
        connectionDetector = new ConnectionDetector(activity);
    }


    // interface to set message
    public interface SnackbarMessage {
        void setSnackbarMessage(String status, boolean showBar, View layout);
    }

    SnackBarClass.SnackbarMessage snackbarMessage;

    public void readySnackbarMessage(SnackBarClass.SnackbarMessage snackbarMessage) {
        this.snackbarMessage = snackbarMessage;
    }



    public void showToast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }


    public void registerInternetCheckReceiver(View layout) {
        IntentFilter internetFilter = new IntentFilter();
        internetFilter.addAction("android.net.wifi.STATE_CHANGE");
        internetFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.layout = layout;
        activity.registerReceiver(broadcastReceiver, internetFilter);
    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String status = connectionDetector.getConnectivityStatusString(context);
            snackbarMessage.setSnackbarMessage(status, false, layout);
     //       setSnackbarMessage(status, false);
        }
    };


}
