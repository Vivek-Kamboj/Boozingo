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
import com.boozingo.details.detailsActivityPub;
import com.boozingo.model.smallBarDetails;
import com.boozingo.model.smallPubDetails;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.boozingo.Boozingo.url;
import static com.boozingo.bars_n_pubs.bars_n_pubs.internetStatus;
import static com.boozingo.bars_n_pubs.bars_n_pubs.pubs;

public class FragPub extends Fragment implements Adapter_pub.ItemClickCallback {

    public List<smallPubDetails> mDataset = new ArrayList<>();
    RecyclerView recview;
    Adapter_pub adapter;
    int j = 0, i = 0;
    smallPubDetails smallDetail;

    private MarshmallowPermissions marshmallowPermissions;


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

        marshmallowPermissions = new MarshmallowPermissions(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_bar, container, false);
        recview = rootView.findViewById(R.id.recycler);
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
        if (internetStatus.equals(getString(R.string.net))) {
            Intent i = new Intent(getActivity(), detailsActivityPub.class);
            i.putExtra("type", "pub");
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


        for (i = 0; i < pubs.length(); i++) {
            JSONObject object = pubs.getJSONObject(i);
            String pic;


            Gson gson = new Gson();
            smallDetail = gson.fromJson(object.toString(), smallPubDetails.class);

            pic = object.getString("pub_icon");
            pic = url + "/storage/" + pic;

            smallDetail.setPub_icon(pic);
            mDataset.add(smallDetail);
        }

        adapter.notifyDataSetChanged();
    }

    public void search(String searchString) {

        List<smallPubDetails> tempDataset = new ArrayList<>();

        if (TextUtils.isEmpty(searchString))
            tempDataset.addAll(mDataset);
        else
            for (smallPubDetails currentX : mDataset) {
                if (currentX.getPub_name().toLowerCase().contains(searchString))
                    tempDataset.add(currentX);
            }

        adapter = new Adapter_pub(tempDataset, getContext());
        recview.setAdapter(adapter);
        adapter.setItemClickCallback(this);

    }
}