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
import com.nith.nimbus2k22.screens.quiz.AllQuizzesFragment;
import com.nith.nimbus2k22.screens.sigma.SigmaFragment;

public class MainActivity extends AppCompatActivity {
    private  int flag = 1;
    private BottomNavigationItemView one;
    private BottomNavigationItemView two;
    private BottomNavigationItemView three;
    private BottomNavigationItemView four;
    private BottomNavigationItemView five;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = findViewById(R.id.home_icon);
        two = findViewById(R.id.Quizzes_icon);
        three = findViewById(R.id.eventsAndWorkshop_icon);
        four = findViewById(R.id.sigma_icon);
        five = findViewById(R.id.profile_icon);



        on_Click();
        one.setOnClickListener(new View.OnClickListener() {
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
                one.setVisibility(View.INVISIBLE);
                two.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);

                break;
            case 2:
                replaceFragment(new AllQuizzesFragment());
                two.setVisibility(View.INVISIBLE);
                one.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);

                break;
            case 3:
                replaceFragment(new AllEventsAndWorkshopsFragment());
                three.setVisibility(View.INVISIBLE);
                two.setVisibility(View.VISIBLE);
                one.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);

                break;
            case 4:
                replaceFragment(new SigmaFragment());
                four.setVisibility(View.INVISIBLE);
                two.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                one.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);

                break;
            case 5:
                replaceFragment(new ViewProfileFragment());
                five.setVisibility(View.INVISIBLE);
                two.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                one.setVisibility(View.VISIBLE);

                break;
        }

    }
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame_layout,fragment);
        fragmentTransaction.commit();
    }


}