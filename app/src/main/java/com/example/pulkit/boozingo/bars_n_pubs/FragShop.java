package com.example.pulkit.boozingo.bars_n_pubs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.bars_n_pubs.Adapter_shop;
import com.example.pulkit.boozingo.details.detailsActivityShop;
import com.example.pulkit.boozingo.model.smallShopDetails;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.pulkit.boozingo.Boozingo.url;
import static com.example.pulkit.boozingo.bars_n_pubs.bars_n_pubs.shops;

public class FragShop extends Fragment implements Adapter_shop.ItemClickCallback {

    public static List<smallShopDetails> mDataset = new ArrayList<>();
    RecyclerView recview;
    Adapter_shop adapter;
    smallShopDetails smallDetail;

    public FragShop() {
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

        mDataset.clear();

        View rootView = inflater.inflate(R.layout.fragment_bar, container, false);
        recview = (RecyclerView) rootView.findViewById(R.id.recycler);
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
        Intent i = new Intent(getActivity(), detailsActivityShop.class);
        i.putExtra("type","shop");
        i.putExtra("id",mDataset.get(p).getId());
        startActivity(i);
    }

    private void initDataset() throws JSONException {

        for (int i=0; i < shops.length(); i++) {
            JSONObject object = shops.getJSONObject(i);
            String pic;


            Gson gson = new Gson();
            smallDetail = gson.fromJson(object.toString(), smallShopDetails.class);
            pic = object.getString("beer_shop_images");

            int comma = pic.indexOf(',');
            if(comma == -1)
                comma = pic.length()-1;

            pic = pic.substring(2,comma-1);
            pic = pic.replaceAll("\\\\", "");

            pic = url +"/storage/" +pic;

            smallDetail.setShop_pic(pic);
            mDataset.add(smallDetail);
            adapter.notifyDataSetChanged();
        }
    }
}