package com.boozingo.bars_n_pubs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.boozingo.SearchAnimationToolbar;
import com.boozingo.ToolbarActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.boozingo.R;
import com.boozingo.helper.DBHelper;
import com.boozingo.helper.HttpHandler;
import com.boozingo.helper.ImageUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.boozingo.Boozingo.*;

public class bars_n_pubs extends AppCompatActivity implements SearchAnimationToolbar.OnSearchQueryChangedListener {

    public CoordinatorLayout layout;
    SearchAnimationToolbar toolbar;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter adapter;

    String city;
    TextView city_name;
    ImageView city_image;
    ProgressDialog pDialog;
    public static JSONArray bars = new JSONArray(), pubs = new JSONArray(), shops = new JSONArray(),
            lounges = new JSONArray(), clubs = new JSONArray();

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();


    byte[] bytes;

    int currentFragmentIndex = 0;

    FragBar fragBar = new FragBar();
    FragPub fragPub = new FragPub();
    FragLounge fragLounge = new FragLounge();
    FragBeer_shop fragBeer_shop = new FragBeer_shop();
    FragNight_club fragNight_club = new FragNight_club();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.temp8);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        init();

        toolbar.setSupportActionBar(bars_n_pubs.this);
        toolbar.setOnSearchQueryChangedListener(bars_n_pubs.this);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        city = getIntent().getStringExtra("city");
        //city = "delhi";

        toolbar.setVisibility(View.GONE);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset <= 100) {
                    //     collapsingToolbarLayout.setTitle(getString(R.string.app_name));
                    //                  collapsingToolbarLayout.setTitle(city.substring(0, 1).toUpperCase() + city.substring(1));
                    toolbar.setVisibility(View.VISIBLE);
                    isShow = true;

                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    toolbar.setVisibility(View.GONE);
                    isShow = false;
                }
            }
        });


        bars = new JSONArray();

        city_name.setText("");


        pDialog = new ProgressDialog(bars_n_pubs.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);
        pDialog.show();

        setupViewPager(viewPager);
        cityDetailsRequest();


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                // Check if this is the page you want.
                currentFragmentIndex = position;
                toolbar.setTitle(mFragmentTitleList.get(position));
                toolbar.clearFocus();
            }
        });
    }

    private void cityDetailsRequest() {

        final String url = URL + city;
        final JsonObjectRequest jsonObjReq;

        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject object = null;
                        try {
                            object = new JSONObject(response.toString());
                            JSONObject data = object.getJSONObject("city_detail");
                            final String pic_url = URL + "/storage/" + data.getString("city_image");
                            bars = object.getJSONArray("bars");

                            //change pubs ro rest
                            pubs = object.getJSONArray("pubs");

                            shops = object.getJSONArray("beer_shops");

                            lounges = object.getJSONArray("lounges");

                            clubs = object.getJSONArray("night_clubs");

                            setupViewPager(viewPager);
                            tabLayout.setupWithViewPager(viewPager);

                            bytes = loadImageFromDB(city + "_full");
                            if (bytes != null) {
                                city_image.setImageBitmap(ImageUtils.getImage(bytes));
                                pDialog.dismiss();
                            } else {

                                RequestOptions options = new RequestOptions()
                                        .centerInside()
                                        .priority(Priority.HIGH);

                                Glide.with(bars_n_pubs.this)
                                        .load(pic_url)
                                        .apply(options)
                                        .into(new SimpleTarget<Drawable>() {
                                            @Override
                                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                                city_image.setImageBitmap(bitmap);
                                                saveImageInDB(bitmap, city + "_full");
                                                pDialog.dismiss();
                                            }
                                        });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()

                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showError(error, bars_n_pubs.this);
                        pDialog.dismiss();
                    }
                });

        // Adding the request to the queue along with a unique String tag
        requestQueue.add(jsonObjReq).setTag(this);
    }

    private void init() {

        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        appBarLayout = findViewById(R.id.app_bar_layout);
        toolbar = findViewById(R.id.toolbar);
        city_name = findViewById(R.id.city_name);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);
        city_image = findViewById(R.id.city_image);
        layout = findViewById(R.id.container);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(fragBar, "Bars");
        adapter.addFragment(fragPub, "Pubs");
        adapter.addFragment(fragLounge, "Lounges");
        adapter.addFragment(fragBeer_shop, "Shops");
        adapter.addFragment(fragNight_club, "Night Clubs");
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {
        boolean handledByToolbar = toolbar.onBackPressed();

        if (!handledByToolbar) {
            super.onBackPressed();
        }
    }

    @Override
    public void onSearchCollapsed() {
        fragBar.search("");
    }

    @Override
    public void onSearchQueryChanged(String query) {
        if (query.equals(""))
            onSearchSubmitted("");
    }

    @Override
    public void onSearchExpanded() {

    }

    @Override
    public void onSearchSubmitted(String query) {

        switch (currentFragmentIndex) {
            case 0:
                fragBar.search(query);
                break;
            case 1:
                fragPub.search(query);
                break;
            case 2:
                fragLounge.search(query);
                break;
            case 3:
                fragBeer_shop.search(query);
                break;
            case 4:
                fragNight_club.search(query);
                break;

        }

        hideSoftInputKeyboard();


    }


    class ViewPagerAdapter extends FragmentPagerAdapter {


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            mFragmentList.clear();
            mFragmentTitleList.clear();
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();


        if (itemId == android.R.id.home) {
            finish();
            return true;
        } else if (itemId == R.id.action_search) {
            toolbar.onSearchIconClick();
            return true;
        } else if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        snackBarClass.registerInternetCheckReceiver(layout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (requestQueue != null) {
            requestQueue.cancelAll(this);
        }
        if (snackBarClass.broadcastReceiver.isOrderedBroadcast())
            unregisterReceiver(snackBarClass.broadcastReceiver);
    }

    public void hideSoftInputKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

}