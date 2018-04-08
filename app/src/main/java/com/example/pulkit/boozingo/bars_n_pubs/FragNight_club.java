package com.example.pulkit.boozingo.bars_n_pubs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pulkit.boozingo.MarshmallowPermissions;
import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.details.detailsActivityNight_club;
import com.example.pulkit.boozingo.model.smallNight_clubDetails;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.pulkit.boozingo.Boozingo.url;
import static com.example.pulkit.boozingo.bars_n_pubs.bars_n_pubs.clubs;
import static com.example.pulkit.boozingo.bars_n_pubs.bars_n_pubs.internetStatus;

public class FragNight_club extends Fragment implements Adapter_club.ItemClickCallback {

    public static List<smallNight_clubDetails> mDataset = new ArrayList<>();
    RecyclerView recview;
    Adapter_club adapter;
    smallNight_clubDetails smallDetail;
    private MarshmallowPermissions marshmallowPermissions;

    public FragNight_club() {
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
        recview = (RecyclerView) rootView.findViewById(R.id.recycler);
        recview.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new Adapter_club(mDataset, getContext());
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

            Intent i = new Intent(getActivity(), detailsActivityNight_club.class);
            i.putExtra("type", "club");
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

        for (int i = 0; i < clubs.length(); i++) {
            JSONObject object = clubs.getJSONObject(i);
            String pic;


            Gson gson = new Gson();
            smallDetail = gson.fromJson(object.toString(), smallNight_clubDetails.class);

            pic = object.getString("night_club_icon");
            pic = url + "/storage/" + pic;

            smallDetail.setNight_club_icon(pic);
            mDataset.add(smallDetail);
            adapter.notifyDataSetChanged();
        }
    }
}