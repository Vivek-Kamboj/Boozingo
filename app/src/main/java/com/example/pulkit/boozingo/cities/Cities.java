package com.example.pulkit.boozingo.cities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.bars_n_pubs.bars_n_pubs;
import com.example.pulkit.boozingo.disclaimer.disclaimer;
import com.example.pulkit.boozingo.helper.HttpHandler;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.pulkit.boozingo.Boozingo.url;

public class Cities extends AppCompatActivity implements View.OnClickListener {

    public static String city = "";
    RelativeLayout rl;
    TextView textView, fact1, fact2;
    EditText search;
    ImageButton img, back;
    ImageView im1, im2, im3, im4, im5, banner, factimg1, factimg2,view_more;
    String TAG = "TAG";
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;
    private Snackbar snackbar;
    private boolean internetConnected = true;
    ProgressDialog pDialog;
    PercentRelativeLayout layout;
    Target target;
    LinearLayout l1, l2, l3, l4, l5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.temp3);

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

        fact1 = (TextView) findViewById(R.id.ct1);
        fact2 = (TextView) findViewById(R.id.ct2);

        fact1.setVisibility(View.GONE);
        fact2.setVisibility(View.GONE);

        l1 = (LinearLayout) findViewById(R.id.city1);
        l2 = (LinearLayout) findViewById(R.id.city2);
        l3 = (LinearLayout) findViewById(R.id.city3);
        l4 = (LinearLayout) findViewById(R.id.city4);
        l5 = (LinearLayout) findViewById(R.id.city5);
        banner = (ImageView) findViewById(R.id.banner);
        layout = (PercentRelativeLayout) findViewById(R.id.layout);


        pDialog = new ProgressDialog(Cities.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);
        pDialog.show();

        new city_search().execute();

        search.setSelection(search.getText().length());

        search.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //     search.setHint("");
                Intent i = new Intent(Cities.this, MainSearch.class);
                startActivity(i);
                return false;
            }
        });


        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        l5.setOnClickListener(this);
        view_more.setOnClickListener(this);
        search.setOnClickListener(this);
        rl.setOnClickListener(this);


        back.setVisibility(View.GONE);

        Uri uri = Uri.parse("http://35.160.58.203/storage/bar-images/July2017/2kcOKeS88yWLcadKLMBz.jpeg");


    }

    @Override
    public void onClick(View v) {
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
        } else if (v == l1) {
            Intent i = new Intent(Cities.this, bars_n_pubs.class);
            i.putExtra("city", "Delhi");
            startActivity(i);
        } else if (v == l2) {
            Intent i = new Intent(Cities.this, bars_n_pubs.class);
            i.putExtra("city", "Lucknow");
            startActivity(i);
        } else if (v == l3) {
            Intent i = new Intent(Cities.this, bars_n_pubs.class);
            i.putExtra("city", "Kanpur");
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
                                                      .priority(Priority.HIGH);

                                              switch (i) {
                                                  case 0:
                                                      Glide.with(Cities.this)
                                                              .load(temp)
                                                              .apply(options)
                                                              .into(im1);

                                                      break;
                                                  case 1:
                                                      Glide.with(Cities.this)
                                                              .load(temp)
                                                              .apply(options)
                                                              .into(im2);
                                                      break;
                                                  case 2:
                                                      Glide.with(Cities.this)
                                                              .load(temp)
                                                              .apply(options)
                                                              .into(im3);
                                                      break;
                                                  case 3:
                                                      Glide.with(Cities.this)
                                                              .load(temp)
                                                              .apply(options)
                                                              .into(im4);

                                                      break;
                                                  case 4:
                                                      Glide.with(Cities.this)
                                                              .load(temp)
                                                              .apply(options)
                                                              .into(im5);
                                                      break;
                                              }

                                          }

                                          n = images.length();

                                          for (int i = 0; i < n - 1; i++) {
                                              JSONObject c = images.getJSONObject(i);

                                              final String image = c.getString("image_url");
                                              String f = c.getString("purpose");

                                              String temp = url + "/storage/" + image;

/*                                        target = new Target() {
                                              @Override
                                              public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                  banner.setBackground(new BitmapDrawable(Cities.this.getResources(), bitmap));
                                                  pDialog.dismiss();
                                              }

                                              @Override
                                              public void onBitmapFailed(Drawable errorDrawable) {

                                              }

                                              @Override
                                              public void onPrepareLoad(Drawable placeHolderDrawable) {

                                              }
                                          };
*/

                                              switch (i) {
                                                  case 0:
                                                      Glide.with(Cities.this)
                                                              .load(temp)
                                                              .into(factimg1);

                                                      fact1.setText(f);
                                                      break;
                                                  case 1:
                                                      Glide.with(Cities.this)
                                                              .load(temp)
                                                              .into(banner);
                                                      break;
                                                  case 2:
                                                      Glide.with(Cities.this)
                                                              .load(temp)
                                                              .into(factimg2);

                                                      fact2.setText(f);

                                                      pDialog.dismiss();
                                                      break;
                                              }
                                          }


                                      } catch (final JSONException e)

                                      {
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
        String internetStatus = "";
        if (status.equalsIgnoreCase("Wifi enabled") || status.equalsIgnoreCase("Mobile data enabled")) {
            internetStatus = "Internet Connected";
        } else {
            internetStatus = "Lost Internet Connection";
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
        if (internetStatus.equalsIgnoreCase("Lost Internet Connection")) {
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

