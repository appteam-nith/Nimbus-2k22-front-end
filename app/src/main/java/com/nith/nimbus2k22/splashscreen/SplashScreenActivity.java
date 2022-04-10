package com.nith.nimbus2k22.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cloudinary.android.MediaManager;
import com.google.firebase.auth.FirebaseAuth;
import com.nith.nimbus2k22.screens.account.LoginActivity;
import com.nith.nimbus2k22.MainActivity;
import com.nith.nimbus2k22.R;

import java.util.HashMap;
import java.util.Map;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    private void initConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dfinmhios");
        config.put("api_key","981293366339261");
        config.put("api_secret","tknXky4p8K5bRT6Aws_xnAnlAFg");
        //  config.put("secure", true);
        MediaManager.init(this, config);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

        initConfig();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                auth = FirebaseAuth.getInstance();
                if (auth.getCurrentUser() == null) {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                }
                else {
                    Intent i = new Intent(SplashScreenActivity.this,
                            MainActivity.class);
                    //Intent is used to switch from one activity to another.

                    startActivity(i);
                    //invoke the SecondActivity.

                    //the current activity will get finished.
                }
                finish();
            }
        }, 40);

    }
}