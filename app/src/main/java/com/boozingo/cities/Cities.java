package com.boozingo.cities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.boozingo.helper.LocationHelper;
import com.boozingo.R;
import com.boozingo.bars_n_pubs.bars_n_pubs;
import com.boozingo.helper.DBHelper;
import com.boozingo.helper.HttpHandler;
import com.boozingo.helper.ImageUtils;
import com.boozingo.helper.Permission;
import com.boozingo.helper.SnackBarClass;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.boozingo.Boozingo.*;
import static com.boozingo.helper.LocationHelper.REQUEST_CHECK_SETTINGS;
import static com.boozingo.helper.LocationHelper.status;

public class Cities extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks {

    RelativeLayout rl;
    TextView textView, t1, t2, t3, t4, t5;
    EditText search;
    ImageView im1, im2, im3, im4, im5, banner, factimg1, factimg2, view_more;
    byte[] bytes;

    ProgressDialog pDialog;
    RelativeLayout layout;
    LinearLayout l1, l2, l3, l4, l5, myCity;

    public static String[] cities_show = new String[4];

    RelativeLayout top;
    HorizontalScrollView hsv;
    CardView c1, c2;
    ImageView boozepedia;
    Space s1, s2;
    int height;

    Permission permission;

    private Location mLastLocation;
    LocationHelper locationHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.temp4);


        // link views to objects
        init();

        // set height and width programatically
        setParams();


        locationHelper = new LocationHelper(this);
        permission = new Permission(this);

        if(!permission.checkPermission())
            permission.requestPermission();

        pDialog = new ProgressDialog(Cities.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        search.setSelection(search.getText().length());

        search.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (internetStatus.equals(getString(R.string.net))) {
                    Intent i = new Intent(Cities.this, MainSearch.class);
                    startActivity(i);
                } else
                    Toast.makeText(Cities.this, "Check network connection.", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        myCity.setOnClickListener(this);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        l5.setOnClickListener(this);
        rl.setOnClickListener(this);
        view_more.setOnClickListener(this);
        search.setOnClickListener(this);

        citySearchRequest();
        boozefactsRequest();

    }


    private void init() {

        textView = findViewById(R.id.text2);
        search = findViewById(R.id.search_text);
        rl = findViewById(R.id.search_button_city);
        im1 = findViewById(R.id.im1);
        im2 = findViewById(R.id.im2);
        im3 = findViewById(R.id.im3);
        im4 = findViewById(R.id.im4);
        im5 = findViewById(R.id.im5);
        view_more = findViewById(R.id.view_more);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);

        factimg1 = findViewById(R.id.cim1);
        factimg2 = findViewById(R.id.cim2);

        top = findViewById(R.id.top);
        hsv = findViewById(R.id.cities);
        c1 = findViewById(R.id.card1);
        c2 = findViewById(R.id.card2);
        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        boozepedia = findViewById(R.id.boozepedia);

        myCity = findViewById(R.id.city0);
        l1 = findViewById(R.id.city1);
        l2 = findViewById(R.id.city2);
        l3 = findViewById(R.id.city3);
        l4 = findViewById(R.id.city4);
        l5 = findViewById(R.id.city5);
        banner = findViewById(R.id.banner);
        layout = findViewById(R.id.layout);

    }

    private void setParams() {

        height = getWindowManager().getDefaultDisplay().getHeight();

        s1.getLayoutParams().height = (int) (height * 0.27);
        s2.getLayoutParams().height = (int) (height * 0.33);
        top.getLayoutParams().height = (int) (height * 0.43);
        hsv.getLayoutParams().height = (int) (height * 0.15);
        boozepedia.getLayoutParams().height = (int) (height * 0.09);
        c1.getLayoutParams().height = (int) (height * 0.25);
        c2.getLayoutParams().height = (int) (height * 0.25);

    }


    @Override
    public void onClick(View v) {

        if (internetStatus.equals(getString(R.string.net))) {
            if (v == search) {
                search.setHint("");
                Intent i = new Intent(Cities.this, MainSearch.class);
                startActivity(i);
            } else if (v == rl) {
               /* Intent i = new Intent(Cities.this, bars_n_pubs.class);
                city = search.getText().toString();
                if (cities_show[0].equals(city) || cities_show[1].equals(city) || cities_show[2].equals(city) || cities_show[3].equals(city)) {
                    if (!TextUtils.isEmpty(city)) {
                        i.putExtra("city", city);
                        startActivity(i);
                    }
                } else
                    Toast.makeText(this, "We will reach your city soon.", Toast.LENGTH_SHORT).show();*/
                search.setHint("");
                Intent i = new Intent(Cities.this, MainSearch.class);
                startActivity(i);
            } else if (v == myCity) {
                Intent i = new Intent(Cities.this, bars_n_pubs.class);
                {
                    // myCity logic
                    /*i.putExtra("city", "myCity city");
                    startActivity(i);*/
                    Toast.makeText(this, "Nearest city", Toast.LENGTH_SHORT).show();
                }
            } else if (v == l1) {
                Intent i = new Intent(Cities.this, bars_n_pubs.class);
                i.putExtra("city", cities_show[0]);
                startActivity(i);
            } else if (v == l2) {
                Intent i = new Intent(Cities.this, bars_n_pubs.class);
                i.putExtra("city", cities_show[1]);
                startActivity(i);
            } else if (v == l3) {
                Intent i = new Intent(Cities.this, bars_n_pubs.class);
                i.putExtra("city", cities_show[2]);
                startActivity(i);
            } else if (v == l4) {
                Intent i = new Intent(Cities.this, bars_n_pubs.class);
                i.putExtra("city", cities_show[3]);
                startActivity(i);
            } else if (v == l5) {
                Intent i = new Intent(Cities.this, bars_n_pubs.class);
                i.putExtra("city", cities_show[4]);
                startActivity(i);
            } else if (v == view_more) {
                Intent i = new Intent(Cities.this, MainSearch.class);
                startActivity(i);
                //        Toast.makeText(this, "We will soon reach your city.", Toast.LENGTH_SHORT).show();
            }
        } else
            Toast.makeText(Cities.this, "Check network connection.", Toast.LENGTH_SHORT).show();
    }


    private void boozefactsRequest() {
        final String url = URL;
        final JsonObjectRequest jsonObjReq;

        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject object = null;
                        try {
                            object = new JSONObject(response.toString());
                            JSONArray images = object.getJSONArray("images");

                            // for banner and booze facts
                            int n = images.length();

                            RequestOptions options2 = new RequestOptions()
                                    .error(R.drawable.booze_fact_error_1)
                                    .override(700, 400);

                            int a = 1, b = 1;

                            String[] temp = new String[n];

                            for (int i = 0; i < n - 1; i++) {
                                JSONObject c = images.getJSONObject(i);
                                final String image = c.getString("image_url");
                                temp[i] = URL + "/storage/" + image;
                            }

                            for (int i = 0; i < n - 1; i++) {
                                switch (i) {
                                    case 0:
                                        bytes = loadImageFromDB("banner");
                                        if (bytes != null) {
                                            factimg1.setImageBitmap(ImageUtils.getImage(bytes));
                                        } else {
                                            Glide.with(Cities.this)
                                                    .load(temp[0])
                                                    .apply(options2)
                                                    .into(new SimpleTarget<Drawable>() {
                                                        @Override
                                                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                            Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                                            banner.setImageBitmap(bitmap);
                                                            saveImageInDB(bitmap, "banner");
                                                        }
                                                    });
                                        }

                                        a = 1 + (int) (Math.random() * ((n - 2)));

                                        do {
                                            b = 1 + (int) (Math.random() * ((n - 2)));
                                        } while (a == b);

                                        break;
                                    case 1:

                                        final int finalA = a;

                                        bytes = loadImageFromDB("booze" + finalA);
                                        if (bytes != null) {
                                            factimg1.setImageBitmap(ImageUtils.getImage(bytes));
                                        } else {
                                            Glide.with(Cities.this)
                                                    .load(temp[finalA])
                                                    .apply(options2)
                                                    .into(new SimpleTarget<Drawable>() {
                                                        @Override
                                                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                            Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                                            factimg1.setImageBitmap(bitmap);
                                                            saveImageInDB(bitmap, "booze" + finalA);
                                                        }
                                                    });
                                        }

                                        break;
                                    case 2:
                                        final int finalB = b;

                                        bytes = loadImageFromDB("booze" + finalB);
                                        if (bytes != null) {
                                            factimg2.setImageBitmap(ImageUtils.getImage(bytes));
                                            if (pDialog.isShowing())
                                                pDialog.dismiss();
                                        } else {
                                            Glide.with(Cities.this)
                                                    .load(temp[finalB])
                                                    .apply(options2)
                                                    .into(new SimpleTarget<Drawable>() {
                                                        @Override
                                                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                            Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                                            factimg2.setImageBitmap(bitmap);
                                                            saveImageInDB(bitmap, "booze" + finalB);

                                                            if (pDialog.isShowing())
                                                                pDialog.dismiss();
                                                        }
                                                    });
                                        }
                                        break;
                                }
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),
                                    "Problem retrieving data. Restart application.",
                                    Toast.LENGTH_LONG)
                                    .show();

                            Glide.with(Cities.this)
                                    .load(R.drawable.booze_fact_error_1)
                                    .into(factimg1);


                            Glide.with(Cities.this)
                                    .load(R.drawable.booze_fact_error_2)
                                    .into(factimg2);

                            pDialog.dismiss();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showError(error, Cities.this);
                        Toast.makeText(getApplicationContext(),
                                "Problem retrieving data. Restart application.",
                                Toast.LENGTH_LONG)
                                .show();

                        Glide.with(Cities.this)
                                .load(R.drawable.booze_fact_error_1)
                                .into(factimg1);


                        Glide.with(Cities.this)
                                .load(R.drawable.booze_fact_error_2)
                                .into(factimg2);

                        pDialog.dismiss();
                    }
                });

        // Adding the request to the queue along with a unique String tag
        requestQueue.add(jsonObjReq).setTag(this);
    }

    private void citySearchRequest() {

        final String url = URL;
        final JsonObjectRequest jsonObjReq;

        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject object = null;
                        try {
                            object = new JSONObject(response.toString());
                            JSONArray cities = object.getJSONArray("cities");

                            // 4 for 4 cities
                            for (int i = 0; i < 4; i++) {
                                JSONObject c = cities.getJSONObject(i);

                                final String city_icon = c.getString("city_icon");
                                final String city_name = c.getString("city_name");
                                cities_show[i] = city_name;

                                String temp = URL + "/storage/" + city_icon;

                                RequestOptions options = new RequestOptions()
                                        .centerCrop()
                                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                                        .apply(RequestOptions.circleCropTransform())
                                        .override(250, 250)
                                        .priority(Priority.HIGH);

                                switch (i) {
                                    case 0:
                                        Glide.with(Cities.this)
                                                .load(temp)
                                                .apply(options)
                                                .into(new SimpleTarget<Drawable>() {
                                                    @Override
                                                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                        Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                                        im1.setImageBitmap(bitmap);
                                                        t1.setText(city_name);
                                                        saveImageInDB(bitmap, city_name);
                                                    }
                                                });

                                        break;
                                    case 1:
                                        Glide.with(Cities.this)
                                                .load(temp)
                                                .apply(options)
                                                .into(new SimpleTarget<Drawable>() {
                                                    @Override
                                                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                        Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                                        im2.setImageBitmap(bitmap);
                                                        t2.setText(city_name);
                                                        saveImageInDB(bitmap, city_name);
                                                    }
                                                });
                                        break;
                                    case 2:
                                        Glide.with(Cities.this)
                                                .load(temp)
                                                .apply(options)
                                                .into(new SimpleTarget<Drawable>() {
                                                    @Override
                                                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                        Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                                        im3.setImageBitmap(bitmap);
                                                        t3.setText(city_name);
                                                        saveImageInDB(bitmap, city_name);
                                                    }
                                                });
                                        break;
                                    case 3:
                                        Glide.with(Cities.this)
                                                .load(temp)
                                                .apply(options)
                                                .into(new SimpleTarget<Drawable>() {
                                                    @Override
                                                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                        Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                                        im4.setImageBitmap(bitmap);
                                                        t4.setText(city_name);
                                                        saveImageInDB(bitmap, city_name);
                                                    }
                                                });

                                        break;
                                                  /*case 4:
                                                      Glide.with(Cities.this)
                                                              .load(temp)
                                                              .apply(options)
                                                              .into(new SimpleTarget<Drawable>() {
                                                                  @Override
                                                                  public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                                      Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                                                      im5.setImageBitmap(bitmap);
                                                                      t5.setText(city_name);
                                                                      saveImageInDB(bitmap, city_name);
                                                                  }
                                                              });
                                                      break;*/
                                }

                            }


                            //to make sure after first time net is checked at this page not at second splash
                            session.create_isfirst();
                            pDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()

                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showError(error, Cities.this);
                        pDialog.dismiss();
                    }
                });

        // Adding the request to the queue along with a unique String tag
        requestQueue.add(jsonObjReq).setTag(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case RESULT_OK:
                        Log.e("d", "d");
                        // All required changes were successfully made
                        mLastLocation = locationHelper.getLocation();
                        break;
                    case RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        try {
                            status.startResolutionForResult(this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        snackBarClass.registerInternetCheckReceiver(layout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*
        if (requestQueue != null) {
            requestQueue.cancelAll(this);
        }*/
        if (snackBarClass.broadcastReceiver.isOrderedBroadcast())
            unregisterReceiver(snackBarClass.broadcastReceiver);
    }

    @Override
    public void onConnected(Bundle arg0) {

        // Once connected with google api, get the location
        mLastLocation = locationHelper.getLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        locationHelper.connectApiClient();
    }


}

