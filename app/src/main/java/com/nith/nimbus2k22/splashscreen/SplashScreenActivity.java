package com.nith.nimbus2k22.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.nith.nimbus2k22.MainActivity;
import com.nith.nimbus2k22.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);


    }
}