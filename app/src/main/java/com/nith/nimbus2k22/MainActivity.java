package com.nith.nimbus2k22;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.cloudinary.android.MediaManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.nith.nimbus2k22.screens.account.ViewProfileFragment;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.AllEventsAndWorkshopsFragment;
import com.nith.nimbus2k22.screens.home.HomeFragment;
import com.nith.nimbus2k22.screens.home.MemeManiaFragment;
import com.nith.nimbus2k22.screens.quiz.AllQuizzesFragment;
import com.nith.nimbus2k22.screens.sigma.SigmaFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private  int flag = 1;
    private ImageView one;
    private ImageView two;
    private ImageView three;
    private ImageView four;
    private ImageView five;
    private ImageView six;
    private ImageView seven;
    private ImageView eight;
    private ImageView nine;
    private ImageView ten;
    private ImageView elven;
    private ImageView twelve;
    private ImageView thirteen;
    private ImageView fourteen;
    private ImageView fifteen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = findViewById(R.id.home_icon);
        two = findViewById(R.id.Quizzes_icon);
        three = findViewById(R.id.eventsAndWorkshop_icon);
        four = findViewById(R.id.sigma_icon);
        five = findViewById(R.id.profile_icon);
        six = findViewById(R.id.home_icon2);
        seven = findViewById(R.id.Quizzes_icon2);
        eight = findViewById(R.id.eventsAndWorkshop_icon2);
        nine = findViewById(R.id.sigma_icon2);
        ten = findViewById(R.id.profile_icon2);
        elven = findViewById(R.id.home_icon3);
        twelve = findViewById(R.id.Quizzes_icon3);
        thirteen = findViewById(R.id.eventsAndWorkshop_icon3);
        fourteen = findViewById(R.id.sigma_icon3);
        fifteen = findViewById(R.id.profile_icon3);


        on_Click();
        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Your code.
                flag = 1;
                on_Click();
            }
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
        two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Your code.
                flag = 2;
                on_Click();
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Your code.
                flag =3;
                on_Click();
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Your code.
                flag =4;
                on_Click();
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Your code.
                flag =5;
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
                six.setVisibility(View.VISIBLE);
                elven.setVisibility(View.VISIBLE);
                seven.setVisibility(View.INVISIBLE);
                eight.setVisibility(View.INVISIBLE);
                nine.setVisibility(View.INVISIBLE);
                ten.setVisibility(View.INVISIBLE);
                twelve.setVisibility(View.INVISIBLE);
                thirteen.setVisibility(View.INVISIBLE);
                fourteen.setVisibility(View.INVISIBLE);
                fifteen.setVisibility(View.INVISIBLE);


                break;
            case 2:
                replaceFragment(new AllQuizzesFragment());

                two.setVisibility(View.INVISIBLE);
                one.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);
                seven.setVisibility(View.VISIBLE);
                twelve.setVisibility(View.VISIBLE);
                six.setVisibility(View.INVISIBLE);
                eight.setVisibility(View.INVISIBLE);
                nine.setVisibility(View.INVISIBLE);
                ten.setVisibility(View.INVISIBLE);
                elven.setVisibility(View.INVISIBLE);
                thirteen.setVisibility(View.INVISIBLE);
                fourteen.setVisibility(View.INVISIBLE);
                fifteen.setVisibility(View.INVISIBLE);

                break;
            case 3:
                replaceFragment(new AllEventsAndWorkshopsFragment());
                three.setVisibility(View.INVISIBLE);
                two.setVisibility(View.VISIBLE);
                one.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);
                eight.setVisibility(View.VISIBLE);
                thirteen.setVisibility(View.VISIBLE);
                seven.setVisibility(View.INVISIBLE);
                six.setVisibility(View.INVISIBLE);
                nine.setVisibility(View.INVISIBLE);
                ten.setVisibility(View.INVISIBLE);
                twelve.setVisibility(View.INVISIBLE);
                elven.setVisibility(View.INVISIBLE);
                fourteen.setVisibility(View.INVISIBLE);
                fifteen.setVisibility(View.INVISIBLE);

                break;
            case 4:
                replaceFragment(new SigmaFragment());
                four.setVisibility(View.INVISIBLE);
                two.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                one.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);
                nine.setVisibility(View.VISIBLE);
                fourteen.setVisibility(View.VISIBLE);
                seven.setVisibility(View.INVISIBLE);
                eight.setVisibility(View.INVISIBLE);
                six.setVisibility(View.INVISIBLE);
                ten.setVisibility(View.INVISIBLE);
                twelve.setVisibility(View.INVISIBLE);
                thirteen.setVisibility(View.INVISIBLE);
                elven.setVisibility(View.INVISIBLE);
                fifteen.setVisibility(View.INVISIBLE);

                break;
            case 5:
                replaceFragment(new ViewProfileFragment());
                five.setVisibility(View.INVISIBLE);
                two.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                one.setVisibility(View.VISIBLE);
                ten.setVisibility(View.VISIBLE);
                fifteen.setVisibility(View.VISIBLE);
                seven.setVisibility(View.INVISIBLE);
                eight.setVisibility(View.INVISIBLE);
                nine.setVisibility(View.INVISIBLE);
                six.setVisibility(View.INVISIBLE);
                twelve.setVisibility(View.INVISIBLE);
                thirteen.setVisibility(View.INVISIBLE);
                fourteen.setVisibility(View.INVISIBLE);
                elven.setVisibility(View.INVISIBLE);

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