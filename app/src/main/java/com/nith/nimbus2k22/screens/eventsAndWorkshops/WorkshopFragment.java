package com.nith.nimbus2k22.screens.eventsAndWorkshops;

import static com.nith.nimbus2k22.apis.EventsVolleyHelper.eventslist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.Models.Events_List;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.adapters.EventsAdapter;
import com.nith.nimbus2k22.apis.EventsVolleyHelper;

import java.util.ArrayList;

public class WorkshopFragment extends Fragment {

    private RecyclerView workshopRV;
    private EventsAdapter workshopAdapter;
    private ArrayList<Events_List> eventsModalArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workshop, container, false);


        workshopRV = view.findViewById(R.id.workshopRV);
        eventsModalArrayList = new ArrayList<>();

        getWorkshopData();

//        buildWorkshopRv();


        return view;
    }

//    private void buildWorkshopRv() {
//
//        workshopAdapter = new EventsAdapter(eventsModalArrayList, getActivity(), false, false);
//
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        workshopRV.setHasFixedSize(true);
//
//        workshopRV.setLayoutManager(manager);
//
//        workshopRV.setAdapter(workshopAdapter);
//
//    }

    private void getWorkshopData() {
        EventsVolleyHelper eventsVolleyHelper = new EventsVolleyHelper(getContext());
        eventsVolleyHelper.getEvents();
        final androidx.lifecycle.Observer<ArrayList<Events_List>> observer = new androidx.lifecycle.Observer<ArrayList<Events_List>>() {
            @Override
            public void onChanged(ArrayList<Events_List> events_lists) {

                ArrayList<Events_List> onlyWorkshop;
                onlyWorkshop = new ArrayList<>();
                for (int i = 0; i < events_lists.size(); i++) {
                    if (events_lists.get(i).getType() == 1) {
                        onlyWorkshop.add(events_lists.get(i));
                    }
                }


                workshopAdapter = new EventsAdapter(onlyWorkshop, getActivity(), false, false);

                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                workshopRV.setHasFixedSize(true);

                workshopRV.setLayoutManager(manager);

                workshopRV.setAdapter(workshopAdapter);
            }
        };
        eventslist.observe(getActivity(), observer);
    }
}
