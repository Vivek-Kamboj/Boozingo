package com.example.pulkit.boozingo.details;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.bars_n_pubs.BarFragment;
import com.example.pulkit.boozingo.model.smallBarDetails;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.example.pulkit.boozingo.bars_n_pubs.BarFragment.mDataset;

public class barDetails extends AppCompatActivity implements OnMapReadyCallback {

    ViewPager viewPager;
    ImageButton back;
    String  images[] = {"http://35.160.58.203/storage/main-images/August2017/WL8ePfP0bnRP2u1fGpe0.jpg",
            "http://35.160.58.203/storage/main-images/August2017/WL8ePfP0bnRP2u1fGpe0.jpg",
            "http://35.160.58.203/storage/main-images/August2017/WL8ePfP0bnRP2u1fGpe0.jpg",
            "http://35.160.58.203/storage/main-images/August2017/WL8ePfP0bnRP2u1fGpe0.jpg",
            "http://35.160.58.203/storage/main-images/August2017/WL8ePfP0bnRP2u1fGpe0.jpg",
            "http://35.160.58.203/storage/main-images/August2017/WL8ePfP0bnRP2u1fGpe0.jpg",};
    picPagerAdapter adapter;
    LinearLayout icons;
    TextView show, speciality, name, type, address, timing;
    Button locator;
    List<String> list = new ArrayList<>();
    String text;
    ImageView transparent, dot1, dot2, dot3, dot4, dot5, dot6;
    ScrollView scroll;
    private Toolbar toolbar;
    smallBarDetails object;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.temp2);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        viewPager = (ViewPager)findViewById(R.id.pager);

        adapter = new picPagerAdapter(barDetails.this, images);
        viewPager.setAdapter(adapter);
        //      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //      toolbar.setTitleTextColor(getResources().getColor(R.color.black_overlay));

        toolbar.setTitle("  ");

        object = mDataset.get(getIntent().getIntExtra("position", 0));

back = (ImageButton) findViewById(R.id.back);
        dot1 = (ImageView) findViewById(R.id.dot1);
        dot2 = (ImageView) findViewById(R.id.dot2);
        dot3 = (ImageView) findViewById(R.id.dot3);
        dot4 = (ImageView) findViewById(R.id.dot4);
        dot5 = (ImageView) findViewById(R.id.dot5);
        dot6 = (ImageView) findViewById(R.id.dot6);

        locator = (Button) findViewById(R.id.locator);
        show = (TextView) findViewById(R.id.show);
        speciality = (TextView) findViewById(R.id.speciality);
        name = (TextView) findViewById(R.id.name);
        type = (TextView) findViewById(R.id.type);
        timing = (TextView) findViewById(R.id.timings);
        address = (TextView) findViewById(R.id.address);
        icons = (LinearLayout) findViewById(R.id.icons);
        transparent = (ImageView) findViewById(R.id.imagetrans);
        scroll = (ScrollView) findViewById(R.id.scroll);


        name.setText(object.getBar_name());
        address.setText(object.getBar_name());
        type.setText("("+ getIntent().getStringExtra("type") +")");
        timing.setText(object.getBar_time());

        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");

        dot1.setImageDrawable(getDrawable(R.drawable.ring));

        text = "It's not what enters men's mouth that is evil, it's what comes out of their mouths that is - Be Responsible it's not what enters men's mouth that is evil, it's what comes out of their mouths that is - Be Responsible it's not what enters men's mouth that is evil, it's what comes out of their mouths that is - Be Responsible";
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        dot1.setImageDrawable(getDrawable(R.drawable.ring));
                        dot2.setImageDrawable(getDrawable(R.drawable.dot));
                        break;

                    case 1:
                        dot1.setImageDrawable(getDrawable(R.drawable.dot));
                        dot2.setImageDrawable(getDrawable(R.drawable.ring));
                        dot3.setImageDrawable(getDrawable(R.drawable.dot));
                        break;
                    case 2:
                        dot2.setImageDrawable(getDrawable(R.drawable.dot));
                        dot3.setImageDrawable(getDrawable(R.drawable.ring));
                        dot4.setImageDrawable(getDrawable(R.drawable.dot));
                        break;
                    case 3:
                        dot3.setImageDrawable(getDrawable(R.drawable.dot));
                        dot4.setImageDrawable(getDrawable(R.drawable.ring));
                        dot5.setImageDrawable(getDrawable(R.drawable.dot));
                        break;
                    case 4:
                        dot4.setImageDrawable(getDrawable(R.drawable.dot));
                        dot5.setImageDrawable(getDrawable(R.drawable.ring));
                        dot6.setImageDrawable(getDrawable(R.drawable.dot));
                        break;
                    case 5:
                        dot5.setImageDrawable(getDrawable(R.drawable.dot));
                        dot6.setImageDrawable(getDrawable(R.drawable.ring));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for (int i = 0; i < 5; i++){
            View child = View.inflate(getBaseContext(), R.layout.smallpicrow, null);
            View x = child.findViewById(R.id.pic);

            switch (i) {
                case 0:
                    x.setBackground(getDrawable(R.drawable.boozingo));
                    break;
                case 1:
                    x.setBackground(getDrawable(R.drawable.glass));
                    break;
                case 2:
                    x.setBackground(getDrawable(R.drawable.serving));
                    break;
                case 3:
                    x.setBackground(getDrawable(R.drawable.cutlery));
                    break;
                case 4:
                    x.setBackground(getDrawable(R.drawable.terrace));
                    break;

            }
            icons.addView(child);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        speciality.setText(text.substring(0, 100) + "...");

        show.setOnClickListener(new View.OnClickListener()

                                {
                                    @Override
                                    public void onClick(View v) {
                                        speciality.setText(text);
                                        show.setVisibility(View.GONE);
                                    }
                                }

        );

        locator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?f=d&hl=en&saddr=" + 24.25 + "," + 75.166 + "&daddr=" + 24.55 + "," + 75.22;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(Intent.createChooser(intent, "Select an application"));
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(24.2602, 76.2541))
                .title("Marker"));

        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(24.2602, 76.2541)));
        map.animateCamera(CameraUpdateFactory.zoomTo(8));
    }

}
