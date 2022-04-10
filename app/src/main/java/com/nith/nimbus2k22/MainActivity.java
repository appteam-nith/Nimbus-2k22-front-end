package com.nith.nimbus2k22;



import static com.nith.nimbus2k22.apis.CoreTeamVolleyHelper.newmemberlist;
import static com.nith.nimbus2k22.apis.FlappyBirdVolleyHelper.flappyscore;
import static com.nith.nimbus2k22.apis.MemesManiaVolleyHelper.Memeslist;

import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.nith.nimbus2k22.Models.FlappyBirdScore;
import com.nith.nimbus2k22.Models.Memes;
import com.nith.nimbus2k22.Models.TeamMemberlist;
import com.nith.nimbus2k22.Models.User_List;
import com.nith.nimbus2k22.apis.CoreTeamVolleyHelper;
import com.nith.nimbus2k22.apis.FlappyBirdVolleyHelper;
import com.nith.nimbus2k22.apis.MemesManiaVolleyHelper;
import com.nith.nimbus2k22.apis.UserVolleyHelper;
import com.nith.nimbus2k22.screens.account.ViewProfileFragment;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.WorkshopFragment;
import com.nith.nimbus2k22.screens.home.HomeFragment;
import com.nith.nimbus2k22.screens.sigma.SigmaFragment;
import com.nith.nimbus2k22.store.StoreFragment;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

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
     CoreTeamVolleyHelper coreTeamVolleyHelper = new CoreTeamVolleyHelper(MainActivity.this);
     coreTeamVolleyHelper.getTeamMembers("Appteam");
     final  androidx.lifecycle.Observer<List<TeamMemberlist>> Ctobs = new androidx.lifecycle.Observer<List<TeamMemberlist>>() {
         @Override
         public void onChanged(List<TeamMemberlist> teamMemberlists) {
             Log.e("teamMemberListabc",teamMemberlists.get(0).getId());
         }
     };
     newmemberlist.observe(this,Ctobs);
FlappyBirdVolleyHelper fp = new FlappyBirdVolleyHelper(MainActivity.this);
fp.getFlappyBirdScore();
final androidx.lifecycle.Observer<List<FlappyBirdScore>> fpobs = new androidx.lifecycle.Observer<List<FlappyBirdScore>>() {
    @Override
    public void onChanged(List<FlappyBirdScore> flappyBirdScores) {
      for(int i=0;i<flappyBirdScores.size();i++){
          Log.e("abcflappy",String.valueOf(flappyBirdScores.get(0).getFirebase()));
      }
    }
};
flappyscore.observe(this,fpobs);
MemesManiaVolleyHelper M3 = new MemesManiaVolleyHelper(MainActivity.this);
M3.createMeme(auth.getUid(),"https://res.cloudinary.com/dfinmhios/image/upload/v1646238049/exkjzlxgzhhhox4wavxc.jpg","Notext","nolocation","NUll");
M3.getMemes();
final androidx.lifecycle.Observer<List<Memes>> memesobs = new androidx.lifecycle.Observer<List<Memes>>() {
    @Override
    public void onChanged(List<Memes> memes) {
        for(int i=0;i<memes.size();i++){
            Log.e("Memesobs",String.valueOf(memes.get(i).getText()));
        }
    }
};
        Memeslist.observe(this,memesobs);

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