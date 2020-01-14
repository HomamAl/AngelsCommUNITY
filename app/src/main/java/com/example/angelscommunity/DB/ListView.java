package com.example.angelscommunity.DB;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.angelscommunity.R;

public class ListView extends RecyclerView.ViewHolder{

    public TextView txt_tieude;
    public TextView txt_nghia;
    public TextView txt_phienam;

    public ListView(View v){
        super(v);
        txt_tieude = v.findViewById(R.id.text_word);
        txt_nghia = v.findViewById(R.id.txt_nghia);
    }

}

