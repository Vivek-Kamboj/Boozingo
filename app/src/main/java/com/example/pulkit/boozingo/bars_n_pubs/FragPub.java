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
import com.example.pulkit.boozingo.details.detailsActivityPub;
import com.example.pulkit.boozingo.model.smallPubDetails;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.pulkit.boozingo.Boozingo.url;
import static com.example.pulkit.boozingo.bars_n_pubs.bars_n_pubs.pubs;

public class FragPub extends Fragment implements Adapter_pub.ItemClickCallback {

    private static final int DATASET_COUNT = 60;
    public static List<smallPubDetails> mDataset = new ArrayList<>();
    RecyclerView recview;
    Adapter_pub adapter;
    int j = 0,i=0;
    smallPubDetails smallDetail;

    public FragPub() {
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

        adapter = new Adapter_pub(mDataset, getContext());
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
        Intent i = new Intent(getActivity(), detailsActivityPub.class);
        i.putExtra("type","bar");
        i.putExtra("id",mDataset.get(p).getId());
        startActivity(i);
    }

    private void initDataset() throws JSONException {


        for (i=0; i < pubs.length(); i++) {
            JSONObject object = pubs.getJSONObject(i);
            String pic;


            Gson gson = new Gson();
            smallDetail = gson.fromJson(object.toString(), smallPubDetails.class);
            pic = object.getString("pub_images");
            pic = pic.substring(2,pic.length()-2);
            pic = url +"/storage/" +pic;

            smallDetail.setPub_pic(pic);
            mDataset.add(smallDetail);
            adapter.notifyDataSetChanged();
        }
    }
}