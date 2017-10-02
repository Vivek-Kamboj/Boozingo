package com.example.pulkit.boozingo.bars_n_pubs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.model.smallShopDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_shop extends RecyclerView.Adapter<Adapter_shop.RecHolder> {

    Context c;

    //interface
    public interface ItemClickCallback {
        void onItemClick(int p);
    }

    Adapter_shop.ItemClickCallback itemClickCallback;

    public void setItemClickCallback(Adapter_shop.ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }


    //adapter
    public List<smallShopDetails> list;
    public LayoutInflater layoutInflater;

    Adapter_shop(List<smallShopDetails> list, Context c) {
        this.list = list;
        this.c = c;
        this.layoutInflater = LayoutInflater.from(c);
    }

    @Override
    public Adapter_shop.RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row, parent, false);
        return new Adapter_shop.RecHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter_shop.RecHolder holder, final int position) {

        holder.name.setText(list.get(position).getShop_name());
        holder.address.setText(list.get(position).getShop_address());
        holder.time.setText(list.get(position).getShop_time());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call = Uri.parse("tel:" + list.get(position).getShop_contact());
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                c.startActivity(surf);
            }
        });

        Picasso.with(c)
                .load(list.get(position).getShop_pic())
                .fit()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setItem(smallShopDetails item, int p) {
        this.list.set(p, item);
    }

    // holder class
    public class RecHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        View view;
        TextView name,address,time;
        ImageView call,image;

        public RecHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            time = (TextView) itemView.findViewById(R.id.time);
            call = (ImageView) itemView.findViewById(R.id.call);
            image = (ImageView) itemView.findViewById(R.id.image);
            view = itemView.findViewById(R.id.container);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == view) {
                itemClickCallback.onItemClick(getAdapterPosition());
            }
        }
    }
}
