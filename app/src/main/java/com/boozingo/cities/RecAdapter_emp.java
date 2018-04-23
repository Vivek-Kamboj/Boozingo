package com.boozingo.cities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boozingo.R;

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
    int bold;

    RecAdapter_emp(List<String> list, Context c, int bold) {
        this.list = list;
        this.layoutInflater = LayoutInflater.from(c);
        this.bold = bold;
    }

    @Override
    public RecAdapter_emp.RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.search_row, parent, false);
        return new RecAdapter_emp.RecHolder(view);
    }

    @Override
    public void onBindViewHolder(RecAdapter_emp.RecHolder holder, int position) {
        SpannableStringBuilder str = new SpannableStringBuilder(list.get(position));
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, bold, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.text.setText(str);
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
        TextView text;

        public RecHolder(View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
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
