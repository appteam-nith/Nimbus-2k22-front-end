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

public class EventFragment extends Fragment {

    private RecyclerView eventRV;
    private EventsAdapter eventsAdapter;
    private ArrayList<EventsModal> eventsModalArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);


        eventRV=view.findViewById(R.id.eventRV);
        eventsModalArrayList= new ArrayList<>();

        getEventData();

        buildEventRv();

        return view;
    }

    private void buildEventRv() {

        eventsAdapter = new EventsAdapter(eventsModalArrayList, getActivity());

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        eventRV.setHasFixedSize(true);

        eventRV.setLayoutManager(manager);

        eventRV.setAdapter(eventsAdapter);

    }

    private void getEventData() {
        for (int i = 0; i < 19; i++) {
            eventsModalArrayList.add(new EventsModal("title","description","startTime","endTime","clubName","platform","imgUrl","regUrl",1));
        }
    }
}