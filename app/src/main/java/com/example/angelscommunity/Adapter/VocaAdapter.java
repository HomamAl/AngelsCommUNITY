package com.example.angelscommunity.Adapter;

import android.app.Activity;
import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelscommunity.DB.Item;
import com.example.angelscommunity.DB.ListView;
import com.example.angelscommunity.OnTapListener;
import com.example.angelscommunity.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VocaAdapter extends RecyclerView.Adapter<ListView> {
    private Activity activity;
    List<Item> items = Collections.emptyList();
    private OnTapListener onTapListener;

    public VocaAdapter(Activity activity, ArrayList<Item> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public ListView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_charities,parent,false);
        return new ListView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListView holder, final int position) {
        holder.txt_tieude.setText(items.get(position).getTieude());
        holder.txt_nghia.setText(items.get(position).getNghia());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onTapListener != null){
                    onTapListener.OnTapView(position);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnTapListener (OnTapListener onTapListener){
        this.onTapListener = onTapListener;
    }
}
