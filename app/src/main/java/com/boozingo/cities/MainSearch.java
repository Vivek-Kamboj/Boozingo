package com.boozingo.cities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.boozingo.R;
import com.boozingo.bars_n_pubs.bars_n_pubs;
import com.boozingo.helper.HttpHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.boozingo.Boozingo.*;
import static com.boozingo.cities.Cities.cities_show;

public class MainSearch extends AppCompatActivity implements RecAdapter_emp.ItemClickCallback {

    private Toolbar toolbar;
    RecyclerView recview;
    RecAdapter_emp adapter;
    List<String> list = new ArrayList<>();
    private List<String> fullList = new ArrayList<>();

    private String TAG = MainSearch.class.getSimpleName();
    EditText search;
    String city;
    int bold;
    int num = 0;
    CoordinatorLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //No title bar is set for the activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.search);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        toolbar = new Toolbar(getBaseContext());
        setSupportActionBar(toolbar);

        search = findViewById(R.id.search);
        layout = findViewById(R.id.layout);

        city = "";

        citySearchRequest();

        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    bold = count;
                    city = search.getText().toString().trim();
                    city = city.toLowerCase();

                    list.clear();

                    for (String string : fullList) {
                        String temp = string.toLowerCase();
                        if (temp.startsWith(city)) {
                            list.add(string);
                        }
                    }

                    if (list.size() == 0) {
                        bold = 0;
                        list.add("No Match Found");
                    }


                    adapter.notifyDataSetChanged();
                    num = 1;

                } else if (count == 0) {

                    bold = 0;
                    list = fullList;
                    adapter.notifyDataSetChanged();
                    num = 0;

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        recview = findViewById(R.id.recycler);
        recview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecAdapter_emp(list, this, bold);
        recview.setAdapter(adapter);
        adapter.setItemClickCallback(MainSearch.this);

    }

    @Override
    public void onItemClick(int p) {

        if (!list.get(p).equals("No Match Found")) {
            if (list.get(p).equals(cities_show[0]) || list.get(p).equals(cities_show[1]) || list.get(p).equals(cities_show[2]) || list.get(p).equals(cities_show[3])) {

                if (internetStatus.equals(getString(R.string.net))) {
                    Intent i = new Intent(MainSearch.this, bars_n_pubs.class);
                    i.putExtra("city", list.get(p));
                    startActivity(i);
                    finish();
                } else
                    Toast.makeText(MainSearch.this, "Check network connection.", Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(this, "We will reach your city soon.", Toast.LENGTH_SHORT).show();

        }

    }

    private void citySearchRequest() {

        final String url = URL + "search";
        final JsonArrayRequest jsonArrayRequest;
        fullList.clear();
        list.clear();


        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray cities = null;

                        try {
                            cities = new JSONArray(response.toString());
                            int n = cities.length();
                            for (int i = 0; i < n; i++) {
                                JSONObject c = cities.getJSONObject(i);

                                String city_name = c.getString("city_name");

                                fullList.add(city_name);
                                list.add(city_name);
                            }

                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()

                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showError(error, MainSearch.this);
                    }
                });

        // Adding the request to the queue along with a unique String tag
        requestQueue.add(jsonArrayRequest).setTag(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        snackBarClass.registerInternetCheckReceiver(layout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (requestQueue != null)
            requestQueue.cancelAll(this);
        if (snackBarClass.broadcastReceiver.isOrderedBroadcast())
            unregisterReceiver(snackBarClass.broadcastReceiver);
    }

}
