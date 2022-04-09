package com.nith.nimbus2k22.screens.home;

import static com.nith.nimbus2k22.apis.CoreTeamVolleyHelper.teamlist;
import static com.nith.nimbus2k22.apis.EventsVolleyHelper.eventslist;
import static com.nith.nimbus2k22.apis.SponsorsVolleyHelper.sponsorslist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nith.nimbus2k22.Models.EventList;
import com.nith.nimbus2k22.Models.Sponsors;
import com.nith.nimbus2k22.Models.TeamList;
import com.nith.nimbus2k22.R;
//import com.nith.nimbus2k22.adapters.SponsorsAdapter;
import com.nith.nimbus2k22.adapters.HomeImgSliderAdapter;
import com.nith.nimbus2k22.adapters.SponsorsHomeAdapter;
import com.nith.nimbus2k22.adapters.TeamsHomeAdapter;
import com.nith.nimbus2k22.adapters.WorkshopHomeAdapter;
import com.nith.nimbus2k22.apis.CoreTeamVolleyHelper;
import com.nith.nimbus2k22.apis.EventsVolleyHelper;
import com.nith.nimbus2k22.apis.SponsorsVolleyHelper;
import com.nith.nimbus2k22.modals.HomeSilderItem;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.WorkshopFragment;
import com.nith.nimbus2k22.screens.sponsors.SponsorsFragment;
import com.nith.nimbus2k22.screens.teams.Teams;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ViewPager2 homeImgSliderVP2;
    private RecyclerView homeworkshopRV;
    private RecyclerView homesponsorRV, homeTeamsRV;

    private TextView homeWorkshopBtn, homesponsorsBtn, homeTemasBtn;

    private WorkshopHomeAdapter eventsAdapter;
    private SponsorsHomeAdapter sponsorsAdapter;
    private TeamsHomeAdapter teamnAdapter;

    private LinearLayout sliderDotsPanel;
    private int dotsCount;
    private ImageView[] dots;
    private int currentItem;


    Handler handler ;
    Runnable r ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Log.d("SATYAM_DEBUG", " AFTER CHANGES");

        homeImgSliderVP2 = view.findViewById(R.id.home_img_slider_VP2);
        sliderDotsPanel = view.findViewById(R.id.slider_dots_panel);

        ArrayList<HomeSilderItem> sliderItemList = new ArrayList<>();
        sliderItemList.add(new HomeSilderItem(R.drawable.hover_drone_home));
        sliderItemList.add(new HomeSilderItem(R.drawable.meme_mania_home));
        sliderItemList.add(new HomeSilderItem(R.drawable.cyberverse_home));

        HomeImgSliderAdapter viewPagerAdapter = new HomeImgSliderAdapter(sliderItemList, homeImgSliderVP2,requireActivity());
        homeImgSliderVP2.setAdapter(viewPagerAdapter);


        // auto img slider



            handler = new Handler();
            r = new Runnable() {
                @Override
                public void run() {
                    currentItem = homeImgSliderVP2.getCurrentItem();
                    Log.d("AUTO_SLIDER", String.valueOf(currentItem));
                    if (currentItem == 2) {
                        currentItem = -1;
                    }
                    currentItem++;
                    homeImgSliderVP2.setCurrentItem(currentItem, true);
                    handler.postDelayed(this, 2500);
                }

            };
        handler.postDelayed(r, 2500);




        // indicator dots
        dotsCount = viewPagerAdapter.getItemCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageResource(R.drawable.inactive_dot);


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);
            sliderDotsPanel.addView(dots[i], params);
        }


        dots[0].setImageResource(R.drawable.active_dot);
        homeImgSliderVP2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.inactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));
            }
        });




        // Events_RV
        homeworkshopRV = view.findViewById(R.id.home_workshop_RV);
        getWorkshopData();

        //sponsors_RV
        homesponsorRV = view.findViewById(R.id.home_sponsors_RV);
        getSponsorsData();

        //teams_RV
        homeTeamsRV=view.findViewById(R.id.home_teams_RV);
        getTeamsData();

        //btns
        homeWorkshopBtn=view.findViewById(R.id.home_workshop_btn);
        homesponsorsBtn=view.findViewById(R.id.home_sponsors_btn);
        homeTemasBtn=view.findViewById(R.id.home_team_btn);


        homeWorkshopBtn.setOnClickListener(view1 -> {
            replaceFragment(new WorkshopFragment(),view);
        });
        homesponsorsBtn.setOnClickListener(view1 -> {
            replaceFragment(new SponsorsFragment(),view);
        });
        homeTemasBtn.setOnClickListener(view1 -> {
//            replaceFragment(new Teams(),view);
        });



        return view;
    }

    private void getTeamsData() {

        CoreTeamVolleyHelper coreTeamVolleyHelper = new CoreTeamVolleyHelper(getContext());
        coreTeamVolleyHelper.getTeamList();
        final androidx.lifecycle.Observer<ArrayList<TeamList>> observer = new androidx.lifecycle.Observer<ArrayList<TeamList>>() {
            @Override
            public void onChanged(ArrayList<TeamList> teamLists) {




                teamnAdapter = new TeamsHomeAdapter(teamLists, getActivity());
                Log.d("TEAM_IMG", teamLists.get(0).getImage());

                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                homeTeamsRV.setHasFixedSize(true);

                homeTeamsRV.setLayoutManager(manager);

                homeTeamsRV.setAdapter(teamnAdapter);
            }
        };
        teamlist.observe(getActivity(), observer);
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




                sponsorsAdapter = new SponsorsHomeAdapter(sponsors_list, getActivity());

                Log.d("SPONSORS", sponsors_list.get(1).getName());

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
        final androidx.lifecycle.Observer<ArrayList<EventList>> observer = new androidx.lifecycle.Observer<ArrayList<EventList>>() {
            @Override
            public void onChanged(ArrayList<EventList> events_lists) {

                eventsAdapter = new WorkshopHomeAdapter(events_lists, getActivity());

                Log.d("EVENTS", events_lists.get(1).getName());

                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                homeworkshopRV.setHasFixedSize(true);

                homeworkshopRV.setLayoutManager(manager);

                homeworkshopRV.setAdapter(eventsAdapter);
            }
        };
        eventslist.observe(getActivity(), observer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(r);
    }
}