package com.example.pulkit.boozingo.disclaimer;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
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

import com.example.pulkit.boozingo.cities.Cities;
import com.example.pulkit.boozingo.R;

public class disclaimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //No title bar is set for the activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_disclaimer);

        final Dialog dialog = new Dialog(disclaimer.this);
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
        ss.setSpan(new UnderlineSpan(),54,74,0);
        ss.setSpan(new StyleSpan(Typeface.BOLD),54,74,0);

        TextView text = (TextView) dialog.findViewById(R.id.text2);
        text.setText(ss);
        text.setMovementMethod(LinkMovementMethod.getInstance());
        text.setHighlightColor(Color.TRANSPARENT);

        Button dialogButton1 = (Button) dialog.findViewById(R.id.dialogButton1);
        Button dialogButton2 = (Button) dialog.findViewById(R.id.dialogButton2);
        dialogButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(disclaimer.this,Cities.class));
            }
        });

        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });

        dialog.setCancelable(false);
        dialog.show();
    }
}
