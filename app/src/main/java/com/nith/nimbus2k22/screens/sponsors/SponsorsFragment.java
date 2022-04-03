package com.nith.nimbus2k22.screens.sponsors;

import static com.nith.nimbus2k22.apis.SponsorsVolleyHelper.sponsorslist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.Models.Sponsors;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.adapters.SponsorsAdapter;
import com.nith.nimbus2k22.apis.SponsorsVolleyHelper;

import java.util.ArrayList;

public class SponsorsFragment extends Fragment {

    private RecyclerView sponsorsRV;
    //    private ArrayList<Sponsors> sponsorsModalArrayList;
    private SponsorsAdapter sponsorsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);

        sponsorsRV = view.findViewById(R.id.sponsors_RecV);
//        sponsorsModalArrayList = new ArrayList<>();

        getSponsorsData();
//        buildSponsorsRV();

        return view;
    }

//    private void buildSponsorsRV() {
//
//
//        sponsorsAdapter = new SponsorsAdapter(sponsorsModalArrayList, getActivity(), false);
//
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        sponsorsRV.setHasFixedSize(true);
//
//        sponsorsRV.setLayoutManager(manager);
//
//        sponsorsRV.setAdapter(sponsorsAdapter);
//
//    }

    private void getSponsorsData() {

//        for (int i = 0; i < 19; i++) {
//            sponsorsModalArrayList.add(new SponsorsModal("name", "link", "image", "position", 1));
//        }


        SponsorsVolleyHelper sponsorsVolleyHelper = new SponsorsVolleyHelper(getContext());
        sponsorsVolleyHelper.getSponsors();
        final androidx.lifecycle.Observer<ArrayList<Sponsors>> observer = new androidx.lifecycle.Observer<ArrayList<Sponsors>>() {
            @Override
            public void onChanged(ArrayList<Sponsors> sponsors_list) {


//                Log.d("xxxxx", sponsors_list.get(0).getName());


                sponsorsAdapter = new SponsorsAdapter(sponsors_list, getActivity(), false,false);

                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                sponsorsRV.setHasFixedSize(true);

                sponsorsRV.setLayoutManager(manager);

                sponsorsRV.setAdapter(sponsorsAdapter);
            }
        };
        sponsorslist.observe(getActivity(), observer);
    }
}