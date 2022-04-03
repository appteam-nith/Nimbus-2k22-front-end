package com.nith.nimbus2k22.screens.home;

//import static com.nith.nimbus2k22.apis.EventsVolleyHelper.evlist;
import static com.nith.nimbus2k22.apis.EventsVolleyHelper.eventslist;
import static com.nith.nimbus2k22.apis.SponsorsVolleyHelper.sponsorslist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nith.nimbus2k22.Models.Events_List;
import com.nith.nimbus2k22.Models.Sponsors;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.adapters.EventsAdapter;
import com.nith.nimbus2k22.adapters.HomeImgSliderAdapter;
import com.nith.nimbus2k22.adapters.SponsorsAdapter;
import com.nith.nimbus2k22.apis.EventsVolleyHelper;
import com.nith.nimbus2k22.apis.SponsorsVolleyHelper;
import com.nith.nimbus2k22.modals.HomeSilderItem;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.EventFragment;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.WorkshopFragment;
import com.nith.nimbus2k22.screens.sponsors.SponsorsFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager2 homeImgSliderVP2;
    private RecyclerView homeEventRV;
    private RecyclerView homeworkshopRV;
    private RecyclerView homesponsorRV;

    private TextView homeEventBtn, homeWorkshopBtn, homesponsorsBtn;


    private EventsAdapter eventsAdapter;
    private SponsorsAdapter sponsorsAdapter;

    private LinearLayout sliderDotsPanel;
    private int dotsCount;
    private ImageView[] dots;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        homeImgSliderVP2 = view.findViewById(R.id.home_img_slider_VP2);
        sliderDotsPanel = view.findViewById(R.id.slider_dots_panel);

        List<HomeSilderItem> silderItemList = new ArrayList<>();
        silderItemList.add(new HomeSilderItem(R.drawable.cyberverse_home));
        silderItemList.add(new HomeSilderItem(R.drawable.cyberverse_home));
        silderItemList.add(new HomeSilderItem(R.drawable.cyberverse_home));
        silderItemList.add(new HomeSilderItem(R.drawable.cyberverse_home));
        silderItemList.add(new HomeSilderItem(R.drawable.cyberverse_home));

        HomeImgSliderAdapter viewPagerAdapter = new HomeImgSliderAdapter(silderItemList, homeImgSliderVP2);
        homeImgSliderVP2.setAdapter(viewPagerAdapter);
        dotsCount = viewPagerAdapter.getItemCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageResource(R.drawable.dot);


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);
            sliderDotsPanel.addView(dots[i], params);
        }

        dots[0].setImageResource(R.drawable.black_dot);
        homeImgSliderVP2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.black_dot));
            }
        });

        //Event
        homeEventRV = view.findViewById(R.id.home_event_RV);


        getEventData();


        //Workshop
        homeworkshopRV = view.findViewById(R.id.home_workshop_RV);


        getWorkshopData();


        //sponsors
        homesponsorRV = view.findViewById(R.id.home_sponsors_RV);

//        getSponsorsData();

        //btn
        homeEventBtn=view.findViewById(R.id.home_event_btn);
        homeWorkshopBtn=view.findViewById(R.id.home_workshop_btn);
        homesponsorsBtn=view.findViewById(R.id.home_sponsors_btn);

        homeEventBtn.setOnClickListener(view1 -> {
            replaceFragment(new EventFragment(),view);
        });
        homeWorkshopBtn.setOnClickListener(view1 -> {
            replaceFragment(new WorkshopFragment(),view);
        });
        homesponsorsBtn.setOnClickListener(view1 -> {
            replaceFragment(new SponsorsFragment(),view);
        });



        return view;
    }

    private void replaceFragment(Fragment fragment, View view){

        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame_layout, fragment).addToBackStack(null).commit();

    }

    private void getSponsorsData() {


        SponsorsVolleyHelper sponsorsVolleyHelper = new SponsorsVolleyHelper(getContext());
        sponsorsVolleyHelper.getSponsors();
        final androidx.lifecycle.Observer<ArrayList<Sponsors>> observer = new androidx.lifecycle.Observer<ArrayList<Sponsors>>() {
            @Override
            public void onChanged(ArrayList<Sponsors> sponsors_list) {


                Log.d("xxxxx", sponsors_list.get(0).getName());


                sponsorsAdapter = new SponsorsAdapter(sponsors_list, getActivity(), true,false);

                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                homesponsorRV.setHasFixedSize(true);

                homesponsorRV.setLayoutManager(manager);

                homesponsorRV.setAdapter(sponsorsAdapter);
            }
        };
        sponsorslist.observe(getActivity(), observer);
    }


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


                eventsAdapter = new EventsAdapter(onlyWorkshop, getActivity(), false, true);

                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                homeworkshopRV.setHasFixedSize(true);

                homeworkshopRV.setLayoutManager(manager);

                homeworkshopRV.setAdapter(eventsAdapter);
            }
        };
        eventslist.observe(getActivity(), observer);
    }


    private void getEventData() {
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

                eventsAdapter = new EventsAdapter(onlyEvents, getActivity(), true, false);
//
                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                homeEventRV.setHasFixedSize(true);

                homeEventRV.setLayoutManager(manager);

                homeEventRV.setAdapter(eventsAdapter);
            }
        };
        eventslist.observe(getActivity(), observer);
    }
}