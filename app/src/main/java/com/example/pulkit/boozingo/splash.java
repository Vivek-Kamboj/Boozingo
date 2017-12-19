package com.example.pulkit.boozingo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.pulkit.boozingo.disclaimer.disclaimer;
import com.example.pulkit.boozingo.splash_screen.SplashScreen;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        startActivity(new Intent(splash.this, SplashScreen.class));
        finish();
    }
}
