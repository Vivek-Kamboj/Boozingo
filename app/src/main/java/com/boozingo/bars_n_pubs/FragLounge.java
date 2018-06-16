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
import com.boozingo.details.detailsActivityLounge;
import com.boozingo.model.smallBarDetails;
import com.boozingo.model.smallLoungeDetails;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.boozingo.Boozingo.*;
import static com.boozingo.bars_n_pubs.bars_n_pubs.lounges;

public class FragLounge extends Fragment implements Adapter_lounge.ItemClickCallback {

    public List<smallLoungeDetails> mDataset = new ArrayList<>();
    RecyclerView recview;
    Adapter_lounge adapter;
    smallLoungeDetails smallDetail;

    private MarshmallowPermissions marshmallowPermissions;

    public FragLounge() {
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

        adapter = new Adapter_lounge(mDataset, getContext());
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
            Intent i = new Intent(getActivity(), detailsActivityLounge.class);
            i.putExtra("type", "lounge");
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

        for (int i = 0; i < lounges.length(); i++) {
            JSONObject object = lounges.getJSONObject(i);
            String pic;

            Gson gson = new Gson();
            smallDetail = gson.fromJson(object.toString(), smallLoungeDetails.class);

            pic = object.getString("lounge_icon");
            pic = URL + "/storage/" + pic;

            smallDetail.setLounge_icon(pic);
            mDataset.add(smallDetail);
        }

        adapter.notifyDataSetChanged();
    }

    public void search(String searchString){

        List<smallLoungeDetails> tempDataset = new ArrayList<>();

        if(TextUtils.isEmpty(searchString))
            tempDataset = mDataset;
        else
            for(smallLoungeDetails currentX : mDataset) {
                if(currentX.getLounge_name().toLowerCase().contains(searchString))
                    tempDataset.add(currentX);
            }

        adapter = new Adapter_lounge(tempDataset, getContext());
        recview.setAdapter(adapter);
        adapter.setItemClickCallback(this);

    }
}