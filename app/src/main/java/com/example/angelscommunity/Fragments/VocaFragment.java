package com.example.angelscommunity.Fragments;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelscommunity.Adapter.VocaAdapter;
import com.example.angelscommunity.CharitiesDatabase;
import com.example.angelscommunity.DB.Item;
import com.example.angelscommunity.OnTapListener;
import com.example.angelscommunity.R;

import java.util.ArrayList;

public class VocaFragment extends Fragment {
    private RecyclerView recyclerView;
    private CharitiesDatabase charitiesDatabase;
    private ArrayList<Item> arrayList = new ArrayList<Item>();
    private Cursor cursor;
    private VocaAdapter adapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.list_item, container, false);
        recyclerView = viewGroup.findViewById(R.id.recycler_view);
        loadDatabase();
        return viewGroup;
    }

    public void loadDatabase(){
        charitiesDatabase = new CharitiesDatabase(getActivity());
        try {
            charitiesDatabase.checkAndCopyDatabase();
            charitiesDatabase.openDatabase();
        }catch(SQLException e){
            e.printStackTrace();
        }
        try {
            cursor = charitiesDatabase.QueryData("select * rom topic_voca");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Item item = new Item();
                        item.setTieude(cursor.getString(1));
                        item.setNghia(cursor.getString(3));
                        arrayList.add(item);
                    } while (cursor.moveToNext());

                }
            }
        }
}
