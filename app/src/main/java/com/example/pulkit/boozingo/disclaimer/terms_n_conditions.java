package com.example.pulkit.boozingo.disclaimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.disclaimer.RecAdapter;
import com.example.pulkit.boozingo.disclaimer.RecData;

import java.util.List;

public class terms_n_conditions extends AppCompatActivity {
    RecyclerView recview;
    RecAdapter adapter;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //No title bar is set for the activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_terms_n_conditions);

        list = RecData.retList();
        recview = (RecyclerView) findViewById(R.id.recycler);
        recview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecAdapter(list, this);
        recview.setAdapter(adapter);
    }
}
