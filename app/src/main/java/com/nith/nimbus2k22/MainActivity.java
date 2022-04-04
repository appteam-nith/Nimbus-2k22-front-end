package com.nith.nimbus2k22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.cloudinary.android.MediaManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nith.nimbus2k22.screens.account.ViewProfileFragment;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.AllEventsAndWorkshopsFragment;
import com.nith.nimbus2k22.screens.home.HomeFragment;
import com.nith.nimbus2k22.screens.home.MemeManiaFragment;
import com.nith.nimbus2k22.screens.quiz.AllQuizzesFragment;
import com.nith.nimbus2k22.screens.sigma.SigmaFragment;
import com.nith.nimbus2k22.screens.sigma.VideoCallJoining;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(getApplicationContext(), VideoCallJoining.class);
        startActivity(i);

        replaceFragment(new MemeManiaFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home_icon:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.Quizzes_icon:
                    replaceFragment(new AllQuizzesFragment());
                    break;
                case R.id.eventsAndWorkshop_icon:
                    replaceFragment(new AllEventsAndWorkshopsFragment());
                    break;
                case R.id.sigma_icon:


                    break;
                case R.id.profile_icon:
                    replaceFragment(new ViewProfileFragment());
                    break;
            }

            return true;
        });
       initConfig();
    }

    private void initConfig() {
        Map config = new HashMap();
        config.put("cloud_name", "dfinmhios");
        config.put("api_key","981293366339261");
        config.put("api_secret","tknXky4p8K5bRT6Aws_xnAnlAFg");
        //  config.put("secure", true);
        MediaManager.init(this, config);
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame_layout,fragment);
        fragmentTransaction.commit();
    }


}