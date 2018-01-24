package com.example.pulkit.boozingo.cities;

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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.pulkit.boozingo.helper.LocationHelper;
import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.Session;
import com.example.pulkit.boozingo.bars_n_pubs.bars_n_pubs;
import com.example.pulkit.boozingo.helper.DBHelper;
import com.example.pulkit.boozingo.helper.HttpHandler;
import com.example.pulkit.boozingo.helper.ImageUtils;
import com.example.pulkit.boozingo.helper.Permission;
import com.example.pulkit.boozingo.helper.SnackBarClass;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.pulkit.boozingo.Boozingo.url;
import static com.example.pulkit.boozingo.helper.LocationHelper.REQUEST_CHECK_SETTINGS;
import static com.example.pulkit.boozingo.helper.LocationHelper.status;

public class Cities extends AppCompatActivity implements View.OnClickListener,SnackBarClass.SnackbarMessage,
        GoogleApiClient.ConnectionCallbacks{

    public static String city = "";
    RelativeLayout rl;
    TextView textView,t1,t2,t3,t4,t5;
    EditText search;
    ImageButton img;
    ImageView im1, im2, im3, im4, im5, banner, factimg1, factimg2, view_more;
    String TAG = "TAG";


    private Snackbar snackbar;
    private boolean internetConnected = true;
    SnackBarClass snackBarClass;
    String internetStatus;


    ProgressDialog pDialog;
    RelativeLayout layout;
    Target target;
    LinearLayout l1, l2, l3, l4, l5, myCity;

    DBHelper dbHelper;
    Session session;
    byte[] bytes;
    Boolean bool = true;

    String[] cities_show = new String [5];

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


        //initially net is assumed to be connected
        internetStatus = getString(R.string.net);

        //initialise database
        dbHelper = new DBHelper(this);
        session = new Session(getApplicationContext());
        locationHelper = new LocationHelper(this);
        permission = new Permission(this);
        snackBarClass = new SnackBarClass(this);
        snackBarClass.readySnackbarMessage(this);



        pDialog = new ProgressDialog(Cities.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);
        pDialog.show();

        search.setSelection(search.getText().length());

        search.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //     search.setHint("");
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


        Uri uri = Uri.parse("http://35.160.58.203/storage/bar-images/July2017/2kcOKeS88yWLcadKLMBz.jpeg");


        cities_show = session.get_city_list();

        if(cities_show==null){
            bool = false;
            cities_show = new String [5];
        }


        for (int i = 0; bool &&(i < cities_show.length) ; i++) {
            bytes = loadImageFromDB(cities_show[i]);
            if (bytes != null) {
                switch (i) {
                    case 0:
                        t1.setText(cities_show[0]);
                        im1.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                    case 1:
                        t2.setText(cities_show[1]);
                        im2.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                    case 2:
                        t3.setText(cities_show[2]);
                        im3.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                    case 3:
                        t4.setText(cities_show[3]);
                        im4.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                    case 4:
                        t5.setText(cities_show[4]);
                        im5.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                }
            } else
                bool = false;
        }


        if (!bool)
            new city_search().execute();

        new boozefacts().execute();

    }


    private void init() {

        img = (ImageButton) findViewById(R.id.search_button_city_2);
        textView = (TextView) findViewById(R.id.text2);
        search = (EditText) findViewById(R.id.search_text);
        rl = (RelativeLayout) findViewById(R.id.search_button_city);
        im1 = (ImageView) findViewById(R.id.im1);
        im2 = (ImageView) findViewById(R.id.im2);
        im3 = (ImageView) findViewById(R.id.im3);
        im4 = (ImageView) findViewById(R.id.im4);
        im5 = (ImageView) findViewById(R.id.im5);
        view_more = (ImageView) findViewById(R.id.view_more);

        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        t5 = (TextView) findViewById(R.id.t5);

        factimg1 = (ImageView) findViewById(R.id.cim1);
        factimg2 = (ImageView) findViewById(R.id.cim2);

        top = (RelativeLayout) findViewById(R.id.top);
        hsv = (HorizontalScrollView) findViewById(R.id.cities);
        c1 = (CardView) findViewById(R.id.card1);
        c2 = (CardView) findViewById(R.id.card2);
        s1 = (Space) findViewById(R.id.s1);
        s2 = (Space) findViewById(R.id.s2);
        boozepedia = (ImageView) findViewById(R.id.boozepedia);

        myCity = (LinearLayout) findViewById(R.id.city0);
        l1 = (LinearLayout) findViewById(R.id.city1);
        l2 = (LinearLayout) findViewById(R.id.city2);
        l3 = (LinearLayout) findViewById(R.id.city3);
        l4 = (LinearLayout) findViewById(R.id.city4);
        l5 = (LinearLayout) findViewById(R.id.city5);
        banner = (ImageView) findViewById(R.id.banner);
        layout = (RelativeLayout) findViewById(R.id.layout);

    }

    private void setParams() {

        height = getWindowManager().getDefaultDisplay().getHeight();

        s1.getLayoutParams().height = (int) (height * 0.29);
        s2.getLayoutParams().height = (int) (height * 0.35);
        top.getLayoutParams().height = (int) (height * 0.45);
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
                Intent i = new Intent(Cities.this, bars_n_pubs.class);
                city = search.getText().toString();
                if (!TextUtils.isEmpty(city)) {
                    i.putExtra("city", city);
                    startActivity(i);
                }
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
            }
        } else
            Toast.makeText(Cities.this, "Check network connection.", Toast.LENGTH_SHORT).show();
    }


    private class boozefacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            HttpHandler sh = new HttpHandler();
            final String jsonStr = sh.makeServiceCall(url);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (jsonStr != null) {
                        try {
                            JSONObject object = new JSONObject(jsonStr);
                            JSONArray images = object.getJSONArray("images");

                            // for banner and booze facts
                            int n = images.length();

                            RequestOptions options2 = new RequestOptions()
                                    .error(R.drawable.booze_fact_error_1)
                                    .override(700, 400);

                            int a = 1, b = 1;

                            String[] temp = new String [n];

                            for (int i = 0; i < n-1; i++) {
                                JSONObject c = images.getJSONObject(i);
                                final String image = c.getString("image_url");
                                temp[i] = url + "/storage/" + image;
                            }

                            for (int i = 0; i < n-1; i++){
                                switch (i) {
                                    case 0:
                                        bytes = loadImageFromDB("banner");
                                        if (bytes != null) {
                                            factimg1.setImageBitmap(ImageUtils.getImage(bytes));
                                        }else {
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

                        } catch (final JSONException e) {
                            Log.e(TAG, "Json parsing error: " + e.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
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

                        }
                    } else {



                        Log.e(TAG, "Couldn't get json from server.");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Network problem. Check network connection.",
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
                    }


                }
            });
            return null;
        }
    }

    private class city_search extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();


            // Making a request to url and getting response
            final String jsonStr = sh.makeServiceCall(url);

            runOnUiThread(new Runnable() {
                              @Override
                              public void run() {

                                  Log.e(TAG, "Response from url: " + jsonStr);

                                  if (jsonStr != null) {
                                      try {
                                          JSONObject object = new JSONObject(jsonStr);
                                          JSONArray cities = object.getJSONArray("cities");

                                          // 5 for 5 cities
                                          for (int i = 0; i < 5; i++) {
                                              JSONObject c = cities.getJSONObject(i);

                                              final String city_icon = c.getString("city_icon");
                                              final String city_name = c.getString("city_name");
                                              cities_show[i] = city_name;

                                              session.create_city_list(cities_show);

                                              String temp = url + "/storage/" + city_icon;

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
                                                  case 4:
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
                                                      break;
                                              }

                                          }


                                          //to make sure after first time net is checked at this page not at second splash
                                          session.create_isfirst();


                                      } catch (final JSONException e) {
                                          Log.e(TAG, "Json parsing error: " + e.getMessage());
                                          runOnUiThread(new Runnable() {
                                              @Override
                                              public void run() {
                                                  Toast.makeText(getApplicationContext(),
                                                          "Problem retrieving data. Restart application.",
                                                          Toast.LENGTH_LONG)
                                                          .show();
                                                  pDialog.dismiss();

                                              }
                                          });

                                      }

                                  } else {
                                      Log.e(TAG, "Couldn't get json from server.");
                                      runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {
                                              Toast.makeText(getApplicationContext(),
                                                      "Network problem. Check network connection.",
                                                      Toast.LENGTH_LONG)
                                                      .show();
                                              pDialog.dismiss();

                                          }
                                      });
                                  }
                              }
                          }

            );


            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }


    //
    void saveImageInDB(Bitmap bitmap, String id) {

        dbHelper.open();
        byte[] inputData = ImageUtils.getImageBytes(bitmap);
        dbHelper.insertImage(inputData, id);
        dbHelper.close();

    }

    byte[] loadImageFromDB(String id) {

        byte[] bytes = null;
        try {
            dbHelper.open();
            bytes = dbHelper.retreiveImageFromDB(id);
            dbHelper.close();
        } catch (Exception e) {
            Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
            dbHelper.close();
        }

        return bytes;
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
    public void setSnackbarMessage(String status, boolean showBar) {

        if (status.equalsIgnoreCase("Wifi enabled") || status.equalsIgnoreCase("Mobile data enabled")) {

            search.setText(city);
            search.setSelection(city.length());
            internetStatus = getString(R.string.net);
        } else {
            internetStatus = getString(R.string.no_net);
        }
        snackbar = Snackbar
                .make(layout, internetStatus, Snackbar.LENGTH_LONG)
                .setAction("X", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                    }
                });
        // Changing message text color
        snackbar.setActionTextColor(Color.WHITE);
        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        if (internetStatus.equalsIgnoreCase(getString(R.string.no_net))) {
            if (internetConnected) {
                snackbar.show();
                internetConnected = false;
            }
        } else {
            if (!internetConnected) {
                internetConnected = true;
                snackbar.show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        snackBarClass.registerInternetCheckReceiver();
        search.setText(city);
        search.setSelection(city.length());

        if (!permission.checkPermission())
            permission.requestPermission();
    }

    @Override
    protected void onPause() {
        super.onPause();
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

