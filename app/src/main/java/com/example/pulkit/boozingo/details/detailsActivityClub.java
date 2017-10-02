package com.example.pulkit.boozingo.details;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pulkit.boozingo.LocationUtil.LocationFinder;
import com.example.pulkit.boozingo.LocationUtil.LocationHelper;
import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.helper.HttpHandler;
import com.example.pulkit.boozingo.model.detailsClub;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.pulkit.boozingo.Boozingo.url;

public class detailsActivityClub extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback {

    ViewPager viewPager;
    ImageButton back;
    String TAG = "TAG", id = "2", text, geo_location, latitudeClub, longitudeClub, image;
    String images[]= new String[6];
    picPagerAdapter adapter;
    LinearLayout icons;
    TextView show, speciality, name, type, address, timing;
    Button locator;
    List<String> list = new ArrayList<>();
    ImageView transparent, dot1, dot2, dot3, dot4, dot5, dot6;
    ScrollView scroll;
    detailsClub details;
    ProgressDialog pDialog;
    SupportMapFragment mapFragment;

    private Location mLastLocation;
    double latitude;
    double longitude;
    LocationHelper locationHelper;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.temp);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // location initialise
        locationHelper = new LocationHelper(this);
        locationHelper.checkpermission();

        id = getIntent().getStringExtra("id");

        viewPager = (ViewPager) findViewById(R.id.pager);

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


        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        pDialog = new ProgressDialog(detailsActivityClub.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);
        pDialog.show();

        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");


        if (locationHelper.checkPlayServices()) {
            locationHelper.buildGoogleApiClient();

        }

        new Handler().postDelayed(new Runnable() {
            public void run() {
                mLastLocation = locationHelper.getLocation();
                if (mLastLocation != null) {
                    latitude = mLastLocation.getLatitude();
                    longitude = mLastLocation.getLongitude();


                }
            }
        }, 1000);


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

        for (int i = 0; i < 15; i++) {
            View child = View.inflate(getBaseContext(), R.layout.smallpicrow, null);
            View x = child.findViewById(R.id.pic);

            switch (i) {
                case 0:
                    x.setBackground(getDrawable(R.drawable.boozingo));
                    x.setContentDescription("Hail Boozingo");
                    break;
                case 1:
                    x.setBackground(getDrawable(R.drawable.booze));
                    x.setContentDescription("Booze Served");

                    break;
                case 2:
                    x.setBackground(getDrawable(R.drawable.food));
                    x.setContentDescription("Food Served");
                    break;
                case 3:
                    x.setBackground(getDrawable(R.drawable.cutlery));
                    break;
                case 4:
                    x.setBackground(getDrawable(R.drawable.sitting));
                    x.setContentDescription("Sitting Available");
                    break;
                default:
                    x.setBackground(getDrawable(R.drawable.music));
                    x.setContentDescription("Music Available");
                    break;
            }
            icons.addView(child);
        }
        speciality.setText(text.substring(0, 100) + "...");

        show.setOnClickListener(new View.OnClickListener() {
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
                String uri = "http://maps.google.com/maps?f=d&hl=en&saddr=" + latitude + "," + longitude + "&daddr=" + latitudeClub + "," + longitudeClub;
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

        new net().execute();

    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(Float.parseFloat(latitudeClub), Float.parseFloat(longitudeClub)))
                .title("Marker"));

        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Float.parseFloat(latitudeClub), Float.parseFloat(longitudeClub))));
        map.animateCamera(CameraUpdateFactory.zoomTo(8));
    }


    private class net extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url + "/club/" + id);


            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    final JSONObject object = new JSONObject(jsonStr);
                    JSONArray array = object.getJSONArray("clubs_detail");

                    JSONObject temp = array.getJSONObject(0);
                    String userJson = temp.toString();

                    Gson gson = new Gson();

                    details = new detailsClub();
                    details = gson.fromJson(userJson, detailsClub.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            name.setText(details.getClub_name());
                            address.setText(details.getClub_address());
                            type.setText("(" + getIntent().getStringExtra("type") + ")");
                            timing.setText(details.getClub_time());
                            geo_location = details.getClub_geolocation();


                            int comma = geo_location.indexOf(',');
                            latitudeClub = geo_location.substring(0, comma);
                            longitudeClub = geo_location.substring(comma + 1);

                            mapFragment.getMapAsync(detailsActivityClub.this);


                            try {
                                image = object.getJSONArray("club_images").getJSONObject(0).getString("club_images");
                                image = image.substring(2, image.length() - 2);
                                image = image.replaceAll("\\\\", "");

                                for(int i=0,x=0;i<image.length();x++){
                                    int j = image.indexOf(',',i);
                                    if(j==-1) {
                                        images[x] = url + "/storage/" +  image.substring(i, image.length());
                                        break;
                                    }
                                    else
                                        images[x] = url + "/storage/" +  image.substring(i,j-1);
                                    i=j+2;
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            adapter = new picPagerAdapter(detailsActivityClub.this, images);
                            viewPager.setAdapter(adapter);

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        locationHelper.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onResume() {
        super.onResume();
        locationHelper.checkPlayServices();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i("Connection failed:", " ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
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


    // Permission check functions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // redirects to utils
        locationHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
