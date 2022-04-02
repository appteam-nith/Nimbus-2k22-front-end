package com.nith.nimbus2k22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nith.nimbus2k22.flappybird.FlappyBird;
import com.nith.nimbus2k22.screens.account.ViewProfileFragment;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.AllEventsAndWorkshopsFragment;
import com.nith.nimbus2k22.screens.home.HomeFragment;
import com.nith.nimbus2k22.screens.home.MemeManiaFragment;
import com.nith.nimbus2k22.screens.quiz.AllQuizzesFragment;
import com.nith.nimbus2k22.screens.sigma.SigmaFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(new MemeManiaFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home_icon:
//                    replaceFragment(new HomeFragment());
                    Intent i=new Intent(this, FlappyBird.class);
                    startActivity(i);
                    break;
                case R.id.Quizzes_icon:
                    replaceFragment(new AllQuizzesFragment());
                    break;
                case R.id.eventsAndWorkshop_icon:
                    replaceFragment(new AllEventsAndWorkshopsFragment());
                    break;
                case R.id.sigma_icon:
                    replaceFragment(new SigmaFragment());
                    break;
                case R.id.profile_icon:
                    replaceFragment(new ViewProfileFragment());
                    break;
            }

            return true;
        });

    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame_layout,fragment);
        fragmentTransaction.commit();
    }
}