package com.nith.nimbus2k22;



import android.content.Context;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.nith.nimbus2k22.screens.account.ViewProfileFragment;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.WorkshopFragment;
import com.nith.nimbus2k22.screens.home.HomeFragment;
import com.nith.nimbus2k22.screens.sigma.SigmaFragment;
import com.nith.nimbus2k22.screens.store.StoreFragment;

public class MainActivity extends AppCompatActivity {
    private int flag = 1;
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
    FirebaseAuth auth;
    Context context = MainActivity.this;
    String idToken = "";
    String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        String id = auth.getUid();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        FileInputStream serviceAccount = new FileInputStream("");
//        FirebaseOptions options = new FirebaseOptions.Builder().set

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
                flag = 3;
                on_Click();
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Your code.
                flag = 4;
                on_Click();
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Your code.
                flag = 5;
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
                replaceFragment(new StoreFragment());

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
                replaceFragment(new WorkshopFragment());
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

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame_layout, fragment);
        fragmentTransaction.commit();
    }
}