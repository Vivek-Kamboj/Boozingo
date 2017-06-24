package com.example.pulkit.boozingo.cities;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.helper.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cities extends AppCompatActivity implements RecAdapter_emp.ItemClickCallback {


    TextView city1, city2, city3, city4, city5, city6;
    JSONObject json;
    public static ArrayList<String> attractions_name, attractions_timing, attractions_overview, attractions_image,
            hotel_name, hotel_location, hotel_overview, hotel_rate, hotel_image,
            rest_name, rest_overview, rest_location, rest_timing, rest_image;

    String city;
    HashMap<String, String> contact = new HashMap<>();

    List<String> city_list = new ArrayList<>();
    TextView textView;
    EditText search;
    private ProgressDialog pDialog;
    private static String url = "http://35.160.58.203/";
    private String TAG = Cities.class.getSimpleName();
    RecyclerView recview;
    RecAdapter_emp adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //No title bar is set for the activity
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_cities);

        recview = (RecyclerView) findViewById(R.id.recycler);
        recview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecAdapter_emp(city_list, this);
        recview.setAdapter(adapter);

        adapter.setItemClickCallback(this);

        textView = (TextView) findViewById(R.id.text2);
        search = (EditText) findViewById(R.id.search_text);

  //      new net().execute();


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 3) {
                    city = search.getText().toString();
                    new city_search().execute();

                } else{
                    search.setBackground(getResources().getDrawable(R.drawable.rounded_corners));
                    city_list.clear();
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onItemClick(int p) {
        String s = city_list.get(p);
        if(!s.equals("No Match Found")){
            search.setText(s);
            search.setSelection(s.length());
        }
    }


    private class net extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Cities.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray cities = jsonObj.getJSONArray("cities");


                    JSONObject c = cities.getJSONObject(0);

                    String city_name = c.getString("city_name");

                    // adding each child node to HashMap key => value
                    contact.put("name", city_name);


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
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            textView.setText(contact.get("name"));
        }
    }

    private class city_search extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url + "search/" + city);

            Log.e(TAG, "Response from url: " + jsonStr);

            city_list.clear();
            if (jsonStr != null) {
                try {
                    JSONArray cities = new JSONArray(jsonStr);

                    int n = cities.length();
                    for (int i = 0; i < n; i++) {
                        JSONObject c = cities.getJSONObject(i);

                        String city_name = c.getString("city_name");

                        city_list.add(city_name);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                search.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
                            }
                        });

                    }

                    if(n==0){
                        city_list.add("No Match Found");
                    }

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
            search.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
            adapter.notifyDataSetChanged();

        }
    }


}

