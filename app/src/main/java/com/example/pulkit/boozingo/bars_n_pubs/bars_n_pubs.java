package com.example.pulkit.boozingo.bars_n_pubs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.helper.HttpHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.pulkit.boozingo.Boozingo.url;

public class bars_n_pubs extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String city;
    TextView city_name;
    ImageView city_image;
    String TAG = "TAG";
    ProgressDialog pDialog;
    public static JSONArray bars = new JSONArray(), pubs = new JSONArray(), shops = new JSONArray(),
            lounges = new JSONArray(), clubs = new JSONArray();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bars_n_pubs);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bars = new JSONArray();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        city = getIntent().getStringExtra("city");
   //    city = "Lucknow";

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.transparent));
        city_name = (TextView) findViewById(R.id.city_name);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        city_image = (ImageView) findViewById(R.id.city_image);

        city_name.setText(city);


        pDialog = new ProgressDialog(bars_n_pubs.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);
        pDialog.show();


        setupViewPager(viewPager);
 //       tabLayout.setupWithViewPager(viewPager);

        new net().execute();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragBar(), "Bars");
        adapter.addFragment(new FragPub(), "Pubs");
        adapter.addFragment(new FragLounge(), "Lounges");
        adapter.addFragment(new FragShop(), "Shops");
        adapter.addFragment(new FragClub(), "Night Club");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private class net extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            final String jsonStr = sh.makeServiceCall(url + "/" + city);


            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject object = new JSONObject(jsonStr);
                    JSONObject data = object.getJSONObject("city_detail");
                    final String pic_url = url + "/storage/"+ data.getString("city_image");
                    bars = object.getJSONArray("bars");

                    //change pubs ro rest
                    pubs = object.getJSONArray("pubs");

                    shops = object.getJSONArray("beer_shops");

                    lounges = object.getJSONArray("lounges");

                    clubs = object.getJSONArray("night_clubs");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            setupViewPager(viewPager);
                            tabLayout.setupWithViewPager(viewPager);

                            Picasso.with(bars_n_pubs.this)
                                    .load(pic_url)
                                    .fit()
                                    .into(city_image);

                            pDialog.dismiss();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }
}