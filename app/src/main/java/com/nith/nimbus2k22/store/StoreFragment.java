package com.nith.nimbus2k22.store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.Models.StoreList;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.adapters.StoreAdapter;

import java.util.ArrayList;

public class StoreFragment extends Fragment {

    private RecyclerView storeRV;
    private ArrayList<StoreList> storeListArrayList;
    private StoreAdapter storeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        storeRV = view.findViewById(R.id.store_RV);
        storeListArrayList = new ArrayList<>();


        getStoreData();
        buildRV();


        return view;
    }

    private void buildRV() {

        storeAdapter = new StoreAdapter(getActivity(),storeListArrayList);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        storeRV.setHasFixedSize(true);

        storeRV.setLayoutManager(manager);

        storeRV.setAdapter(storeAdapter);
    }

    private void getStoreData() {
        for (int i = 0; i < 19; i++) {
            storeListArrayList.add(new StoreList("Treasure Hunt Event", "04 April 2022", 10000, "regUrl","EventDescription","https://media.geeksforgeeks.org/img-practice/banner/dsa-self-paced-thumbnail.png"));
        }
    }
}