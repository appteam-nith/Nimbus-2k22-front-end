package com.nith.nimbus2k22.screens.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.adapters.EventsAdapter;
import com.nith.nimbus2k22.adapters.HomeImgSliderAdapter;
import com.nith.nimbus2k22.adapters.SponsorsAdapter;
import com.nith.nimbus2k22.modals.EventsModal;
import com.nith.nimbus2k22.modals.HomeSilderItem;
import com.nith.nimbus2k22.modals.SponsorsModal;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager2 homeImgSliderVP2;
    private RecyclerView homeEventRV;
    private RecyclerView homeworkshopRV;
    private RecyclerView homesponsorRV;

    private ArrayList<EventsModal> eventsModalArrayList;
    private ArrayList<EventsModal> workshopArrayList;
    private ArrayList<SponsorsModal> sponsorsModalArrayList;

    private EventsAdapter eventsAdapter;
    private SponsorsAdapter sponsorsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        homeImgSliderVP2 = view.findViewById(R.id.home_img_slider_VP2);

        List<HomeSilderItem> silderItemList = new ArrayList<>();
        silderItemList.add(new HomeSilderItem(R.drawable.cyberverse_home));
        silderItemList.add(new HomeSilderItem(R.drawable.cyberverse_home));
        silderItemList.add(new HomeSilderItem(R.drawable.cyberverse_home));
        silderItemList.add(new HomeSilderItem(R.drawable.cyberverse_home));
        silderItemList.add(new HomeSilderItem(R.drawable.cyberverse_home));

        homeImgSliderVP2.setAdapter(new HomeImgSliderAdapter(silderItemList, homeImgSliderVP2));

        //Event
        homeEventRV=view.findViewById(R.id.home_event_RV);
        eventsModalArrayList= new ArrayList<>();

        getEventData();

        buildEventRv();

        //Workshop
        homeworkshopRV=view.findViewById(R.id.home_workshop_RV);
        eventsModalArrayList= new ArrayList<>();

        getWorkshopData();

        buildEventRv();


        //sponsors
        homesponsorRV=view.findViewById(R.id.home_sponsors_RV);
        sponsorsModalArrayList = new ArrayList<>();

        getSponsorsData();
        buildSponsorsRV();

        return view;
    }

    private void getSponsorsData() {

        for (int i = 0; i < 19; i++) {
            sponsorsModalArrayList.add(new SponsorsModal("name", "link", "image", "position", 1));
        }
    }

    private void buildSponsorsRV() {


        sponsorsAdapter = new SponsorsAdapter(sponsorsModalArrayList, getActivity());

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        homesponsorRV.setHasFixedSize(true);

        homesponsorRV.setLayoutManager(manager);

        homesponsorRV.setAdapter(sponsorsAdapter);

    }

    private void getWorkshopData() {
        for (int i = 0; i < 19; i++) {
            eventsModalArrayList.add(new EventsModal("title","description","startTime","endTime","clubName","platform","imgUrl","regUrl",1));
        }
    }

    private void buildEventRv() {

        eventsAdapter = new EventsAdapter(eventsModalArrayList, getActivity(),true);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        homeEventRV.setHasFixedSize(true);

        homeEventRV.setLayoutManager(manager);

        homeEventRV.setAdapter(eventsAdapter);
    }

    private void getEventData() {
        for (int i = 0; i < 19; i++) {
            eventsModalArrayList.add(new EventsModal("title","description","startTime","endTime","clubName","platform","imgUrl","regUrl",1));
        }
    }
}