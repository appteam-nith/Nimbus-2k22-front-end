package com.nith.nimbus2k22;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.nith.nimbus2k22.screens.account.ViewProfileFragment;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.AllEventsAndWorkshopsFragment;
import com.nith.nimbus2k22.screens.home.HomeFragment;
import com.nith.nimbus2k22.screens.sigma.SigmaFragment;

public class MainActivity extends AppCompatActivity {
    private  int flag = 1;
    private BottomNavigationItemView homeBottomNavItem;
    private BottomNavigationItemView eventsBottomNavItem;
    private BottomNavigationItemView sigmaBottomNavItem;
    private BottomNavigationItemView profileBottomNavItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeBottomNavItem = findViewById(R.id.home_icon);
        eventsBottomNavItem = findViewById(R.id.eventsAndWorkshop_icon);
        sigmaBottomNavItem = findViewById(R.id.sigma_icon);
        profileBottomNavItem = findViewById(R.id.profile_icon);

        on_Click();
        homeBottomNavItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Your code.
                flag = 1;
                on_Click();
            }
        });
    }

    private void on_Click() {

        switch (flag) {
            case 1:
                replaceFragment(new HomeFragment());
                homeBottomNavItem.setVisibility(View.VISIBLE);
                eventsBottomNavItem.setVisibility(View.VISIBLE);
                sigmaBottomNavItem.setVisibility(View.VISIBLE);
                profileBottomNavItem.setVisibility(View.VISIBLE);
                break;

            case 2:
                replaceFragment(new AllEventsAndWorkshopsFragment());
                eventsBottomNavItem.setVisibility(View.INVISIBLE);
                homeBottomNavItem.setVisibility(View.VISIBLE);
                sigmaBottomNavItem.setVisibility(View.VISIBLE);
                profileBottomNavItem.setVisibility(View.VISIBLE);
                break;

            case 3:
                replaceFragment(new SigmaFragment());
                sigmaBottomNavItem.setVisibility(View.INVISIBLE);
                eventsBottomNavItem.setVisibility(View.VISIBLE);
                homeBottomNavItem.setVisibility(View.VISIBLE);
                profileBottomNavItem.setVisibility(View.VISIBLE);
                break;

            case 4:
                replaceFragment(new ViewProfileFragment());
                profileBottomNavItem.setVisibility(View.INVISIBLE);
                eventsBottomNavItem.setVisibility(View.VISIBLE);
                sigmaBottomNavItem.setVisibility(View.VISIBLE);
                homeBottomNavItem.setVisibility(View.VISIBLE);
                break;

        }

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame_layout,fragment);
        fragmentTransaction.commit();
    }


}