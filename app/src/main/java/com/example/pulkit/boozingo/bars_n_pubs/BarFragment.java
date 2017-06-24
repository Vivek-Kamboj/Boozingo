package com.example.pulkit.boozingo.bars_n_pubs;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pulkit.boozingo.R;

import java.util.ArrayList;
import java.util.List;

public class BarFragment extends Fragment implements RecAdapter_emp.ItemClickCallback {

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    List<String> mDataset = new ArrayList<>();
    RecyclerView recview;
    RecAdapter_emp adapter;



    public BarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_bar, container, false);
        recview = (RecyclerView) rootView.findViewById(R.id.recycler);
        recview.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new RecAdapter_emp(mDataset,getContext());
        recview.setAdapter(adapter);

        adapter.setItemClickCallback(this);
        return rootView;
    }

    @Override
    public void onItemClick(int p) {
    }

    private void initDataset() {
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset.add("This is element #" + i);
        }
    }

}