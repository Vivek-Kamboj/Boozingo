package com.boozingo.bars_n_pubs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.boozingo.MarshmallowPermissions;
import com.boozingo.R;
import com.boozingo.details.detailsActivityBeer_shop;
import com.boozingo.model.smallBeer_shopDetails;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.boozingo.Boozingo.*;
import static com.boozingo.bars_n_pubs.bars_n_pubs.shops;

public class FragBeer_shop extends Fragment implements Adapter_shop.ItemClickCallback {

    public List<smallBeer_shopDetails> mDataset = new ArrayList<>();
    RecyclerView recview;
    Adapter_shop adapter;
    smallBeer_shopDetails smallDetail;

    private MarshmallowPermissions marshmallowPermissions;

    public FragBeer_shop() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        marshmallowPermissions = new MarshmallowPermissions(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_bar, container, false);
        recview = rootView.findViewById(R.id.recycler);
        recview.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new Adapter_shop(mDataset, getContext());
        recview.setAdapter(adapter);
        adapter.setItemClickCallback(this);


        try {
            initDataset();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @Override
    public void onItemClick(int p) {
        if (internetStatus.equals(getString(R.string.net))) {
            Intent i = new Intent(getActivity(), detailsActivityBeer_shop.class);
            i.putExtra("type", "shop");
            i.putExtra("id", mDataset.get(p).getId());


            if (!marshmallowPermissions.checkPermissionForFineLocation())
                marshmallowPermissions.requestPermissionForFineLocation();

            if (marshmallowPermissions.checkPermissionForFineLocation())
                startActivity(i);
            else {
                Toast.makeText(getActivity(), "Give Permission", Toast.LENGTH_SHORT).show();
                marshmallowPermissions.requestPermissionForFineLocation();
            }
        } else
            Toast.makeText(getActivity(), "Check network connection.", Toast.LENGTH_SHORT).show();

    }

    private void initDataset() throws JSONException {

        for (int i = 0; i < shops.length(); i++) {
            JSONObject object = shops.getJSONObject(i);
            String pic;


            Gson gson = new Gson();
            smallDetail = gson.fromJson(object.toString(), smallBeer_shopDetails.class);

            pic = object.getString("beer_shop_icon");
            pic = URL + "/storage/" + pic;

            smallDetail.setBeer_shop_icon(pic);
            mDataset.add(smallDetail);
        }
        adapter.notifyDataSetChanged();
    }

    public void search(String searchString){

        List<smallBeer_shopDetails> tempDataset = new ArrayList<>();

        if(TextUtils.isEmpty(searchString))
            tempDataset = mDataset;
        else
            for(smallBeer_shopDetails currentX : mDataset) {
                if(currentX.getBeer_shop_name().toLowerCase().contains(searchString))
                    tempDataset.add(currentX);
            }

        adapter = new Adapter_shop(tempDataset, getContext());
        recview.setAdapter(adapter);
        adapter.setItemClickCallback(this);

    }
}