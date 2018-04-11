package com.example.pulkit.boozingo.disclaimer;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pulkit.boozingo.R;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecHolder>{

    //adapter

    public List<String> list;
    public LayoutInflater layoutInflater;

    RecAdapter(List<String> list , Context c){
        this.list = list;
        this.layoutInflater = LayoutInflater.from(c);
    }

    @Override
    public RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.terms_row,parent,false);
        return new RecHolder(view);
    }

    @Override
    public void onBindViewHolder(RecHolder holder, int position) {

        String item = list.get(position);
        holder.text.setText(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    // holder class
    public class RecHolder extends RecyclerView.ViewHolder{

        TextView text;

        public RecHolder(View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text_input);

        }
    }
}
