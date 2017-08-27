package com.example.pulkit.boozingo.cities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.helper.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainSearch extends AppCompatActivity implements RecAdapter_emp.ItemClickCallback {

    private Toolbar toolbar;
    RecyclerView recview;
    RecAdapter_emp adapter;
    List<String> list = new ArrayList<>();
    private List<String> fullList = new ArrayList<>();
    HashMap<String, String> contact = new HashMap<>();
    String type;
    private ProgressDialog pDialog;
    private static String url = "http://35.160.58.203/";
    private String TAG = MainSearch.class.getSimpleName();
    EditText search;
    String city;
    View back;
    int bold;
    int num=0;

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

        search = (EditText) findViewById(R.id.search);
        back = findViewById(R.id.back);

        city = "";
        new city_search().execute();

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


                    adapter = new RecAdapter_emp(list, MainSearch.this, bold);
                    recview.setAdapter(adapter);
                    num=1;
                    adapter.setItemClickCallback(MainSearch.this);

                } else if(count == 0) {

                    bold = 0;
                    adapter = new RecAdapter_emp(fullList, MainSearch.this, bold);
                    recview.setAdapter(adapter);
                    num=0;
                    adapter.setItemClickCallback(MainSearch.this);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recview = (RecyclerView) findViewById(R.id.recycler);
        recview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecAdapter_emp(list, this, bold);
        recview.setAdapter(adapter);
        adapter.setItemClickCallback(MainSearch.this);

    }

    @Override
    public void onItemClick(int p) {
        if(num == 0){
            Cities.city = fullList.get(p);
            finish();
        }
        else if (num ==1) {
            Cities.city = list.get(p);
            finish();
        }
    }


    private class city_search extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url + "search");

            Log.e(TAG, "Response from url: " + jsonStr);

            fullList.clear();
            list.clear();
            if (jsonStr != null) {
                try {
                    JSONArray cities = new JSONArray(jsonStr);

                    int n = cities.length();
                    for (int i = 0; i < n; i++) {
                        JSONObject c = cities.getJSONObject(i);

                        String city_name = c.getString("city_name");

                        fullList.add(city_name);
                        list.add(city_name);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
         //                       search.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
                            }
                        });

                    }

                   /* if(n==0){
                        list.add("No Match Found");
                    }*/

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });


            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
     //       search.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
            adapter.notifyDataSetChanged();

        }
    }

}
