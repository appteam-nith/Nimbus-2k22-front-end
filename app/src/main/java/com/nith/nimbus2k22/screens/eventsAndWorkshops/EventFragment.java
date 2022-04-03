package com.nith.nimbus2k22.screens.eventsAndWorkshops;


import static com.nith.nimbus2k22.apis.EventsVolleyHelper.eventread;
import static com.nith.nimbus2k22.apis.EventsVolleyHelper.eventslist;
//import static com.nith.nimbus2k22.apis.EventsVolleyHelper.evlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.Models.Events_List;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.adapters.EventsAdapter;
import com.nith.nimbus2k22.apis.EventsVolleyHelper;

import java.util.ArrayList;

public class EventFragment extends Fragment {

    private RecyclerView eventRV;
    private EventsAdapter eventsAdapter;
//    private ArrayList<Events_List> eventsModalArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);


        eventRV = view.findViewById(R.id.eventRV);
//        eventsModalArrayList= new ArrayList<>();

        getEventData();

//        buildEventRv();

        return view;
    }

//    public void buildEventRv() {
//
//        eventsAdapter = new EventsAdapter(eventsModalArrayList, getActivity(),false,false);
//
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        eventRV.setHasFixedSize(true);
//
//        eventRV.setLayoutManager(manager);
//
//        eventRV.setAdapter(eventsAdapter);
//
//    }

    public void getEventData() {

        EventsVolleyHelper eventsVolleyHelper = new EventsVolleyHelper(getContext());
        eventsVolleyHelper.getEvents();
        final androidx.lifecycle.Observer<ArrayList<Events_List>> observer = new androidx.lifecycle.Observer<ArrayList<Events_List>>() {
            @Override
            public void onChanged(ArrayList<Events_List> events_lists) {

                ArrayList<Events_List> onlyEvents;
                onlyEvents = new ArrayList<>();
                for (int i = 0; i < events_lists.size(); i++) {
                    if (events_lists.get(i).getType() == 0) {
                        onlyEvents.add(events_lists.get(i));
                    }
                }

                eventsAdapter = new EventsAdapter(onlyEvents, getActivity(), false, false);

                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                eventRV.setHasFixedSize(true);

                eventRV.setLayoutManager(manager);

                eventRV.setAdapter(eventsAdapter);
            }
        };
        eventslist.observe(getActivity(), observer);
    }
}