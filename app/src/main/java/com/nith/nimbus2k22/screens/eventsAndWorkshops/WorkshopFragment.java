package com.nith.nimbus2k22.screens.eventsAndWorkshops;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.adapters.EventsAdapter;
import com.nith.nimbus2k22.modals.EventsModal;

import java.util.ArrayList;

public class WorkshopFragment extends Fragment {

    private RecyclerView workshopRV;
    private EventsAdapter workshopAdapter;
    private ArrayList<EventsModal> eventsModalArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workshop, container, false);


        workshopRV=view.findViewById(R.id.workshopRV);
        eventsModalArrayList= new ArrayList<>();

        getWorkshopData();

        buildWorkshopRv();


        return view;
    }

    private void buildWorkshopRv() {

        workshopAdapter = new EventsAdapter(eventsModalArrayList, getActivity());

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        workshopRV.setHasFixedSize(true);

        workshopRV.setLayoutManager(manager);

        workshopRV.setAdapter(workshopAdapter);

    }

    private void getWorkshopData() {
        for (int i = 0; i < 19; i++) {
            eventsModalArrayList.add(new EventsModal("title","description","startTime","endTime","clubName","platform","imgUrl","regUrl",1));
        }
    }
}