package com.nith.nimbus2k22.screens.store;

import static com.nith.nimbus2k22.apis.EventsVolleyHelper.eventslist;
import static com.nith.nimbus2k22.apis.StoreVolleyHelper.storelist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.Models.EventList;
import com.nith.nimbus2k22.Models.StoreList;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.adapters.StoreAdapter;
import com.nith.nimbus2k22.adapters.WorkshopFragAdapter;
import com.nith.nimbus2k22.apis.EventsVolleyHelper;
import com.nith.nimbus2k22.apis.StoreVolleyHelper;

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


        return view;
    }



    private void getStoreData() {
        StoreVolleyHelper storeVolleyHelper = new StoreVolleyHelper(getContext());
        storeVolleyHelper.getStore();
        final androidx.lifecycle.Observer<ArrayList<StoreList>> observer = new androidx.lifecycle.Observer<ArrayList<StoreList>>() {
            @Override
            public void onChanged(ArrayList<StoreList> store_list) {

                Log.d("StoreDAta", store_list.get(0).getProductName());

                storeAdapter = new StoreAdapter(requireActivity(),store_list);

                LinearLayoutManager manager = new LinearLayoutManager(requireContext());
                storeRV.setHasFixedSize(true);

                storeRV.setLayoutManager(manager);

                storeRV.setAdapter(storeAdapter);
            }



        };
        storelist.observe(requireActivity(), observer);



    }
}