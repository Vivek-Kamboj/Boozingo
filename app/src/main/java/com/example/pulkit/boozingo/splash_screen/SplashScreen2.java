package com.example.pulkit.boozingo.splash_screen;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.disclaimer.disclaimer;

public class SplashScreen2 extends AppCompatActivity {

    TextView text;
    Typeface tf;
    ImageView image;
    RelativeLayout rel, layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //No title bar is set for the activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen2);
        text = (TextView) findViewById(R.id.text);
        image = (ImageView) findViewById(R.id.image);
        rel = (RelativeLayout) findViewById(R.id.relativeLayout);
        layout = (RelativeLayout) findViewById(R.id.layout);

        tf = Typeface.createFromAsset(this.getAssets(), "KaushanScript.otf");
        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        final Animation animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        rel.setVisibility(View.GONE);


        text.setTypeface(tf);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                image.startAnimation(animationFadeOut);
            }
        }, 800);

        animationFadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image.setBackgroundColor(Color.WHITE);
                startActivity(new Intent(SplashScreen2.this, disclaimer.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(SplashScreen2.this, disclaimer.class));
                        finish();
                    }
                }, 800);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}


