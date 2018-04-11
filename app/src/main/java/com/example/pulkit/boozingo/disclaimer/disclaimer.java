package com.example.pulkit.boozingo.disclaimer;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pulkit.boozingo.Session;
import com.example.pulkit.boozingo.cities.Cities;
import com.example.pulkit.boozingo.R;

import java.net.InetAddress;

public class disclaimer extends AppCompatActivity {

    Dialog dialog;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //No title bar is set for the activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        session = new Session(getApplicationContext());

        if (session.isolduser()) {
            startActivity(new Intent(disclaimer.this, Cities.class));
            finish();
        }

        setContentView(R.layout.activity_disclaimer);

    }


    @Override
    protected void onStop() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        super.onStop();
    }

    @Override
    protected void onResume() {
        dialog = new Dialog(disclaimer.this);
        dialog.setContentView(R.layout.disclaimer);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        SpannableString ss = new SpannableString(getResources().getString(R.string.disclaimer));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(disclaimer.this, terms_n_conditions.class));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 53, 75, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new UnderlineSpan(), 54, 74, 0);
        ss.setSpan(new StyleSpan(Typeface.BOLD), 54, 74, 0);

        TextView text = dialog.findViewById(R.id.text2);
        text.setText(ss);
        text.setMovementMethod(LinkMovementMethod.getInstance());
        text.setHighlightColor(Color.TRANSPARENT);

        Button dialogButton1 = dialog.findViewById(R.id.dialogButton1);
        Button dialogButton2 = dialog.findViewById(R.id.dialogButton2);
        dialogButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getConnectivityStatus(disclaimer.this)) {
                    startActivity(new Intent(disclaimer.this, Cities.class));
                    session.create_oldusersession();
                    finish();
                } else
                    Toast.makeText(disclaimer.this, "Check network connectivity.", Toast.LENGTH_SHORT).show();

            }
        });

        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dialog.setCancelable(false);
        dialog.show();

        super.onResume();
    }


    public boolean getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return  (activeNetwork != null && activeNetwork.isAvailable() && activeNetwork.isConnected());
    }
}
