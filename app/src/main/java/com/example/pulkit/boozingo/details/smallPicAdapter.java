package com.example.pulkit.boozingo.details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.pulkit.boozingo.R;

import java.util.List;

public class smallPicAdapter extends RecyclerView.Adapter<smallPicAdapter.picHolder> {


    public List<String> list;
    Context context;

    public smallPicAdapter(List<String> list, Context c) {
        this.list = list;
        this.context = c;
    }

    @Override
    public picHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.smallpicrow, parent, false);
        return new picHolder(view);
    }

    @Override
    public void onBindViewHolder(picHolder holder, int position) {

        String string = list.get(position);
/*        switch (position){
            case 0: holder.pic.setImageResource(R.drawable.boozingo);
                break;
            case 1: holder.pic.setImageResource(R.drawable.glass);
                break;
            case 2: holder.pic.setImageResource(R.drawable.serving);
                break;
            case 3: holder.pic.setImageResource(R.drawable.cutlery);
                break;
            case 4: holder.pic.setImageResource(R.drawable.terrace);
                break;
            case 5: holder.pic.setImageResource(R.drawable.cutlery);
                break;
            case 6: holder.pic.setImageResource(R.drawable.open_book);
                break;
        }*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setItem(String item, int p) {
        this.list.set(p, item);
    }

    // holder class
    public class picHolder extends RecyclerView.ViewHolder {

        ImageView pic;

        public picHolder(View itemView) {
            super(itemView);

            pic = (ImageView) itemView.findViewById(R.id.pic);

        }
    }


}
