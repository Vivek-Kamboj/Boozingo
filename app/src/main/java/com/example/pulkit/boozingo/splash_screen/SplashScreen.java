package com.example.pulkit.boozingo.splash_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;

import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.Session;
import com.example.pulkit.boozingo.disclaimer.disclaimer;

import static com.example.pulkit.boozingo.cities.Cities.getConnectivityStatusString;

public class SplashScreen extends AppCompatActivity {

    private Snackbar snackbar;
    RelativeLayout layout;
    Session session;
    boolean isAppInstalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //No title bar is set for the activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);
        session = new Session(getApplicationContext());

        isAppInstalled = session.isappInstalled();
        if(!isAppInstalled) {
            //  create short code

            Intent shortcutIntent = new Intent(getApplicationContext(),SplashScreen.class);
            shortcutIntent.setAction(Intent.ACTION_MAIN);
            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Boozingo");
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource
                    .fromContext(getApplicationContext(), R.mipmap.logo));
            intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            getApplicationContext().sendBroadcast(intent);

            //Make preference false

            session.create_isappInstalled();
        }

        layout = (RelativeLayout) findViewById(R.id.layout);
 //       startActivity(new Intent(SplashScreen.this, disclaimer.class));
        String status = getConnectivityStatusString(this);
        setSnackbarMessage(status, false);



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
        }
        else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(SplashScreen.this, disclaimer.class));
                    finish();
                }
            }, 1500);
        }

    }
}
