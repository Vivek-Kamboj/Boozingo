package com.example.pulkit.boozingo.bars_n_pubs;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.details.barDetails;
import com.example.pulkit.boozingo.helper.HttpHandler;
import com.example.pulkit.boozingo.model.smallBarDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.pulkit.boozingo.bars_n_pubs.bars_n_pubs.bars;

public class BarFragment extends Fragment implements Adapter_bars.ItemClickCallback {

    private static final int DATASET_COUNT = 60;
    public static List<smallBarDetails> mDataset = new ArrayList<>();
    RecyclerView recview;
    Adapter_bars adapter;
    int j = 0,i=0;

    public BarFragment() {
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

        adapter = new Adapter_bars(mDataset, getContext());
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
        Intent i = new Intent(getActivity(), barDetails.class);
        i.putExtra("type","bar");
        i.putExtra("position",p);
        startActivity(i);
    }

    private void initDataset() throws JSONException {

        for (i=0; i < bars.length(); i++) {
            JSONObject object = bars.getJSONObject(i);
            String address, name, timing,contact;


            name = object.getString("bar_name");
            address = object.getString("bar_address");
            timing = object.getString("bar_time");
            contact = object.getString("bar_contact");

            smallBarDetails temp = new smallBarDetails(address,contact,"",name,"",timing,"");
            mDataset.add(temp);
            adapter.notifyDataSetChanged();
        }
    }
}