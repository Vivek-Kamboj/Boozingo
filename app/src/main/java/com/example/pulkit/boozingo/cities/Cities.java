package com.example.pulkit.boozingo.cities;

import android.app.ProgressDialog;
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
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.percent.PercentRelativeLayout;
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
import com.bumptech.glide.load.model.AssetUriLoader;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.Session;
import com.example.pulkit.boozingo.bars_n_pubs.bars_n_pubs;
import com.example.pulkit.boozingo.helper.DBHelper;
import com.example.pulkit.boozingo.helper.HttpHandler;
import com.example.pulkit.boozingo.helper.ImageUtils;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import static com.example.pulkit.boozingo.Boozingo.url;

public class Cities extends AppCompatActivity implements View.OnClickListener {

    public static String city = "";
    RelativeLayout rl;
    TextView textView;
    EditText search;
    ImageButton img, back;
    ImageView im1, im2, im3, im4, im5, banner, factimg1, factimg2, view_more;
    String TAG = "TAG";
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;
    private Snackbar snackbar;
    private boolean internetConnected = true;
    ProgressDialog pDialog;
    RelativeLayout layout;
    Target target;
    LinearLayout l1, l2, l3, l4, l5, myCity;
    String internetStatus;

    DBHelper dbHelper;
    Session session;
    byte[] bytes;
    Boolean bool = true;
    String[] cache = {"booze1", "booze2", "lucknow", "delhi", "bengaluru", "mumbai", "pune", "banner"};

    RelativeLayout top;
    HorizontalScrollView hsv;
    CardView c1, c2;
    ImageView boozepedia;
    Space s1, s2;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.temp4);

        height = getWindowManager().getDefaultDisplay().getHeight();

        img = (ImageButton) findViewById(R.id.search_button_city_2);
        back = (ImageButton) findViewById(R.id.back);
        textView = (TextView) findViewById(R.id.text2);
        search = (EditText) findViewById(R.id.search_text);
        rl = (RelativeLayout) findViewById(R.id.search_button_city);
        im1 = (ImageView) findViewById(R.id.im1);
        im2 = (ImageView) findViewById(R.id.im2);
        im3 = (ImageView) findViewById(R.id.im3);
        im4 = (ImageView) findViewById(R.id.im4);
        im5 = (ImageView) findViewById(R.id.im5);
        view_more = (ImageView) findViewById(R.id.view_more);

        factimg1 = (ImageView) findViewById(R.id.cim1);
        factimg2 = (ImageView) findViewById(R.id.cim2);

        //initially net is assumed to be connected
        internetStatus = getString(R.string.net);

        //initialise database
        dbHelper = new DBHelper(this);
        session = new Session(getApplicationContext());

        top = (RelativeLayout) findViewById(R.id.top);
        hsv = (HorizontalScrollView) findViewById(R.id.cities);
        c1 = (CardView) findViewById(R.id.card1);
        c2 = (CardView) findViewById(R.id.card2);
        s1 = (Space) findViewById(R.id.s1);
        s2 = (Space) findViewById(R.id.s2);
        boozepedia = (ImageView) findViewById(R.id.boozepedia);

        s1.getLayoutParams().height = (int) (height * 0.32);
        s2.getLayoutParams().height = (int) (height * 0.38);
        top.getLayoutParams().height = (int) (height * 0.50);
        hsv.getLayoutParams().height = (int) (height * 0.15);
        boozepedia.getLayoutParams().height = (int) (height * 0.10);
        c1.getLayoutParams().height = (int) (height * 0.30);
        c2.getLayoutParams().height = (int) (height * 0.30);


        myCity = (LinearLayout) findViewById(R.id.city0);
        l1 = (LinearLayout) findViewById(R.id.city1);
        l2 = (LinearLayout) findViewById(R.id.city2);
        l3 = (LinearLayout) findViewById(R.id.city3);
        l4 = (LinearLayout) findViewById(R.id.city4);
        l5 = (LinearLayout) findViewById(R.id.city5);
        banner = (ImageView) findViewById(R.id.banner);
        layout = (RelativeLayout) findViewById(R.id.layout);


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


        back.setVisibility(View.GONE);

        Uri uri = Uri.parse("http://35.160.58.203/storage/bar-images/July2017/2kcOKeS88yWLcadKLMBz.jpeg");


        for (int i = 0; (i < cache.length) && bool; i++) {
            bytes = loadImageFromDB(cache[i]);
            if (bytes != null) {
                switch (i) {
                    case 0:
                        factimg1.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                    case 1:
                        factimg2.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                    case 2:
                        im1.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                    case 3:
                        im2.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                    case 4:
                        im3.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                    case 5:
                        im4.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                    case 6:
                        im5.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                    case 7:
                        banner.setImageBitmap(ImageUtils.getImage(bytes));
                        break;
                }
            } else
                bool = false;
        }


        if (bool)
            pDialog.dismiss();

        if (!bool)
            new city_search().execute();

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
                i.putExtra("city", "Lucknow");
                startActivity(i);
            } else if (v == l2) {
                Intent i = new Intent(Cities.this, bars_n_pubs.class);
                i.putExtra("city", "Delhi");
                startActivity(i);
            } else if (v == l3) {
                Intent i = new Intent(Cities.this, bars_n_pubs.class);
                i.putExtra("city", "Bengaluru");
                startActivity(i);
            } else if (v == l4) {
                Intent i = new Intent(Cities.this, bars_n_pubs.class);
                i.putExtra("city", "Mumbai");
                startActivity(i);
            } else if (v == l5) {
                Intent i = new Intent(Cities.this, bars_n_pubs.class);
                i.putExtra("city", "Pune");
                startActivity(i);
            } else if (v == view_more) {
                Intent i = new Intent(Cities.this, MainSearch.class);
                startActivity(i);
            }
        } else
            Toast.makeText(Cities.this, "Check network connection.", Toast.LENGTH_SHORT).show();
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
                                          JSONArray images = object.getJSONArray("images");

                                          int n = cities.length();
                                          for (int i = 0; i < n - 1; i++) {
                                              JSONObject c = cities.getJSONObject(i);

                                              final String city_name = c.getString("city_image");

                                              String temp = url + "/storage/" + city_name;

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
                                                                      saveImageInDB(bitmap, "lucknow");
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
                                                                      saveImageInDB(bitmap, "delhi");
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
                                                                      saveImageInDB(bitmap, "bengaluru");
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
                                                                      saveImageInDB(bitmap, "mumbai");
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
                                                                      saveImageInDB(bitmap, "pune");
                                                                  }
                                                              });
                                                      break;
                                              }

                                          }

                                          n = images.length();

                                          RequestOptions options2 = new RequestOptions()
                                                  .override(700, 400);

                                          for (int i = 0; i < n - 1; i++) {
                                              JSONObject c = images.getJSONObject(i);

                                              final String image = c.getString("image_url");
                                              String temp = url + "/storage/" + image;

                                              switch (i) {
                                                  case 0:

                                                      Glide.with(Cities.this)
                                                              .load(temp)
                                                              .apply(options2)
                                                              .into(new SimpleTarget<Drawable>() {
                                                                  @Override
                                                                  public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                                      Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                                                      banner.setImageBitmap(bitmap);
                                                                      saveImageInDB(bitmap, "banner");
                                                                  }
                                                              });


                                                      break;
                                                  case 1:

                                                      Glide.with(Cities.this)
                                                              .load(temp)
                                                              .apply(options2)
                                                              .into(new SimpleTarget<Drawable>() {
                                                                  @Override
                                                                  public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                                      Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                                                      factimg1.setImageBitmap(bitmap);
                                                                      saveImageInDB(bitmap, "booze1");
                                                                  }
                                                              });

                                                      break;
                                                  case 2:
                                                      Glide.with(Cities.this)
                                                              .load(temp)
                                                              .apply(options2)
                                                              .into(new SimpleTarget<Drawable>() {
                                                                  @Override
                                                                  public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                                      Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                                                      factimg2.setImageBitmap(bitmap);
                                                                      saveImageInDB(bitmap, "booze2");
                                                                      pDialog.dismiss();
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


    private void registerInternetCheckReceiver() {
        IntentFilter internetFilter = new IntentFilter();
        internetFilter.addAction("android.net.wifi.STATE_CHANGE");
        internetFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastReceiver, internetFilter);
    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String status = getConnectivityStatusString(context);
            setSnackbarMessage(status, false);
        }
    };

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = getConnectivityStatus(context);
        String status = null;
        if (conn == TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }

    private void setSnackbarMessage(String status, boolean showBar) {

        if (status.equalsIgnoreCase("Wifi enabled") || status.equalsIgnoreCase("Mobile data enabled")) {
        /*    // if connection is made after page is build. It needs to be reloaded
            if(internetStatus.equals(getString(R.string.no_net))){
                internetStatus = getString(R.string.net);
                startActivity(new Intent(this,Cities.class));
                finish();
            }*/
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
        registerInternetCheckReceiver();
        search.setText(city);
        search.setSelection(city.length());
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}

