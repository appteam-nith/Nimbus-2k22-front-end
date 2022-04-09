package com.nith.nimbus2k22.screens.eventsAndWorkshops;

import static com.nith.nimbus2k22.apis.EventsVolleyHelper.eventslist;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nith.nimbus2k22.Models.EventList;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.adapters.WorkshopFragAdapter;
import com.nith.nimbus2k22.apis.EventsVolleyHelper;

import java.util.ArrayList;

public class WorkshopFragment extends Fragment {

    private RecyclerView workshopRV;
    private WorkshopFragAdapter workshopAdapter;
    private ArrayList<EventList> eventsModalArrayList;

    private ProgressBar eventPG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workshop, container, false);

        eventPG=view.findViewById(R.id.event_pg);

        workshopRV = view.findViewById(R.id.workshopRV);
        eventsModalArrayList = new ArrayList<>();

        getWorkshopData();


        return view;
    }


    private void getWorkshopData() {
        EventsVolleyHelper eventsVolleyHelper = new EventsVolleyHelper(getContext());
        eventsVolleyHelper.getEvents();
        final androidx.lifecycle.Observer<ArrayList<EventList>> observer = new androidx.lifecycle.Observer<ArrayList<EventList>>() {
            @Override
            public void onChanged(ArrayList<EventList> events_lists) {

                eventPG.setVisibility(View.GONE);
                workshopRV.setVisibility(View.VISIBLE);
                //
                Log.d("WRKSHPDATA", events_lists.get(1).getName());
                //
                workshopAdapter = new WorkshopFragAdapter(events_lists, getActivity());

                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                workshopRV.setHasFixedSize(true);

                workshopRV.setLayoutManager(manager);

                workshopRV.setAdapter(workshopAdapter);
            }
        };
        eventslist.observe(getActivity(), observer);
    }
}
