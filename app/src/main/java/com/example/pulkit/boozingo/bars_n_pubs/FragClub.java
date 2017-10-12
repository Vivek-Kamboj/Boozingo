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
import com.example.pulkit.boozingo.details.detailsActivityClub;
import com.example.pulkit.boozingo.model.smallClubDetails;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.pulkit.boozingo.Boozingo.url;
import static com.example.pulkit.boozingo.bars_n_pubs.bars_n_pubs.clubs;

public class FragClub extends Fragment implements Adapter_club.ItemClickCallback {

    public static List<smallClubDetails> mDataset = new ArrayList<>();
    RecyclerView recview;
    Adapter_club adapter;
    smallClubDetails smallDetail;

    public FragClub() {
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
        Intent i = new Intent(getActivity(), detailsActivityClub.class);
        i.putExtra("type","club");
        i.putExtra("id",mDataset.get(p).getId());
        startActivity(i);
    }

    private void initDataset() throws JSONException {

        for (int i=0; i < clubs.length(); i++) {
            JSONObject object = clubs.getJSONObject(i);
            String pic;


            Gson gson = new Gson();
            smallDetail = gson.fromJson(object.toString(), smallClubDetails.class);
            pic = object.getString("night_club_images");

            int comma = pic.indexOf(',');
            if(comma == -1)
                comma = pic.length()-1;

            pic = pic.substring(2,comma-1);
            pic = pic.replaceAll("\\\\", "");

            pic = url +"/storage/" +pic;

            smallDetail.setClub_pic(pic);
            mDataset.add(smallDetail);
            adapter.notifyDataSetChanged();
        }
    }
}