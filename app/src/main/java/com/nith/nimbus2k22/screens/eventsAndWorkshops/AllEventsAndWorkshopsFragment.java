package com.nith.nimbus2k22.screens.eventsAndWorkshops;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.adapters.EventWorkshopAdapter;

public class AllEventsAndWorkshopsFragment extends Fragment {

    private TabLayout eventTabLayout;
    private ViewPager2 eventViewPager2;
    private EventWorkshopAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_events_and_workshops, container, false);

        eventTabLayout = view.findViewById(R.id.events_tab_layout);
        eventViewPager2 = view.findViewById(R.id.events_TL_viewP2);

        eventTabLayout.addTab(eventTabLayout.newTab().setText("      "));
        eventTabLayout.addTab(eventTabLayout.newTab().setText("      "));

        FragmentManager fragmentManager = getParentFragmentManager();
        adapter = new EventWorkshopAdapter(fragmentManager, getLifecycle());
        eventViewPager2.setAdapter(adapter);

        eventTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                eventViewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        eventViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                eventTabLayout.selectTab(eventTabLayout.getTabAt(position));
            }
        });

        return view;
    }
}