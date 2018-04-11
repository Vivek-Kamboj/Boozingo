package com.example.pulkit.boozingo.bars_n_pubs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pulkit.boozingo.MarshmallowPermissions;
import com.example.pulkit.boozingo.R;
import com.example.pulkit.boozingo.model.smallBarDetails;

import java.util.List;

public class Adapter_bar extends RecyclerView.Adapter<Adapter_bar.RecHolder> {

    Context c;
    private MarshmallowPermissions marshmallowPermissions;
    RequestOptions options;

    //interface
    public interface ItemClickCallback {
        void onItemClick(int p);
    }

    Adapter_bar.ItemClickCallback itemClickCallback;

    public void setItemClickCallback(Adapter_bar.ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }


    //adapter
    public List<smallBarDetails> list;
    public LayoutInflater layoutInflater;

    Adapter_bar(List<smallBarDetails> list, Context c) {
        this.list = list;
        this.c = c;
        this.layoutInflater = LayoutInflater.from(c);
        marshmallowPermissions = new MarshmallowPermissions((Activity) c);
        options = new RequestOptions()
                .error(R.drawable.booze_fact_error_1)
                .override(150, 150);
    }

    @Override
    public Adapter_bar.RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row, parent, false);
        return new Adapter_bar.RecHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter_bar.RecHolder holder, final int position) {

        String add = list.get(position).getBar_address();
        if (add.length() > 80)
            add = add.substring(0, 80) + "...";

        holder.name.setText(list.get(position).getBar_name());
        holder.address.setText(add);
        holder.time.setText(list.get(position).getBar_time());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri call = Uri.parse("tel:" + list.get(position).getBar_contact());
                if (!TextUtils.isEmpty(list.get(position).getBar_contact())) {
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    c.startActivity(surf);
                } else
                    Toast.makeText(c, "Contact not available.", Toast.LENGTH_SHORT).show();
            }
        });

        Glide.with(c)
                .load(list.get(position).getBar_icon())
                .apply(options)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setItem(smallBarDetails item, int p) {
        this.list.set(p, item);
    }

    // holder class
    public class RecHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        View view;
        TextView name, address, time;
        ImageView call, image;

        public RecHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            time = itemView.findViewById(R.id.time);
            call = itemView.findViewById(R.id.call);
            image = itemView.findViewById(R.id.image);
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
