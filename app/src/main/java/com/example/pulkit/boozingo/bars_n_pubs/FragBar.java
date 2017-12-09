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
import com.example.pulkit.boozingo.details.detailsActivityBar;
import com.example.pulkit.boozingo.model.smallBarDetails;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.pulkit.boozingo.Boozingo.url;
import static com.example.pulkit.boozingo.bars_n_pubs.bars_n_pubs.bars;

public class FragBar extends Fragment implements Adapter_bar.ItemClickCallback {

    public static List<smallBarDetails> mDataset = new ArrayList<>();
    RecyclerView recview;
    Adapter_bar adapter;
    smallBarDetails smallDetail;


    private MarshmallowPermissions marshmallowPermissions;


    public FragBar() {
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

        adapter = new Adapter_bar(mDataset, getContext());
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
        Intent i = new Intent(getActivity(), detailsActivityBar.class);
        i.putExtra("type","bar");
        i.putExtra("id",mDataset.get(p).getId());


        if (!marshmallowPermissions.checkPermissionForFineLocation())
            marshmallowPermissions.requestPermissionForFineLocation();

        if(marshmallowPermissions.checkPermissionForFineLocation())
            startActivity(i);
        else{
            Toast.makeText(getActivity(), "Give Permission", Toast.LENGTH_SHORT).show();
            marshmallowPermissions.requestPermissionForFineLocation();
        }

    }

    private void initDataset() throws JSONException {

        for (int i=0; i < bars.length(); i++) {
            JSONObject object = bars.getJSONObject(i);
            String pic;


            Gson gson = new Gson();
            smallDetail = gson.fromJson(object.toString(), smallBarDetails.class);
            pic = object.getString("bar_images");

            int comma = pic.indexOf(',');
            if(comma == -1)
                comma = pic.length()-1;

            pic = pic.substring(2,comma-1);
            pic = pic.replaceAll("\\\\", "");

            pic = url +"/storage/" +pic;

            smallDetail.setBar_pic(pic);
            mDataset.add(smallDetail);
            adapter.notifyDataSetChanged();
        }
    }
}