package com.example.pulkit.boozingo.bars_n_pubs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pulkit.boozingo.R;

import java.util.List;

public class RecAdapter_emp extends RecyclerView.Adapter<RecAdapter_emp.RecHolder> {


    //interface
    public interface ItemClickCallback {
        void onItemClick(int p);
    }

    RecAdapter_emp.ItemClickCallback itemClickCallback;

    public void setItemClickCallback(RecAdapter_emp.ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }


    //adapter
    public List<String> list;
    public LayoutInflater layoutInflater;

    RecAdapter_emp(List<String> list, Context c) {
        this.list = list;
        this.layoutInflater = LayoutInflater.from(c);
    }

    @Override
    public RecAdapter_emp.RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.search_row, parent, false);
        return new RecAdapter_emp.RecHolder(view);
    }

    @Override
    public void onBindViewHolder(RecAdapter_emp.RecHolder holder, int position) {

        holder.name.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setItem(String item, int p) {
        this.list.set(p, item);
    }

    // holder class
    public class RecHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        View view;
        TextView name;

        public RecHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.text);
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
