package com.nith.nimbus2k22.screens.eventsAndWorkshops;


import static com.nith.nimbus2k22.apis.EventsVolleyHelper.eventslist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.Models.EventList;
import com.nith.nimbus2k22.Models.EventList;
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
        final androidx.lifecycle.Observer<ArrayList<EventList>> observer = new androidx.lifecycle.Observer<ArrayList<EventList>>() {
            @Override
            public void onChanged(ArrayList<EventList> events_lists) {

                ArrayList<EventList> onlyEvents;
//                onlyEvents = new ArrayList<>();
//                for (int i = 0; i < events_lists.size(); i++) {
//                    if (events_lists.get(i).getId()==0) {
//                        onlyEvents.add(events_lists.get(i));
//                    }
//                }

                eventsAdapter = new EventsAdapter(events_lists, getActivity(), false, false);

                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                eventRV.setHasFixedSize(true);

                eventRV.setLayoutManager(manager);

                eventRV.setAdapter(eventsAdapter);
            }
        };
        eventslist.observe(getActivity(), observer);
    }
}