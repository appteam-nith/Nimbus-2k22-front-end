package com.nith.nimbus2k22.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

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
 FirebaseAuth auth=FirebaseAuth.getInstance();

    private void initConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dfinmhios");
        config.put("api_key","981293366339261");
        config.put("api_secret","tknXky4p8K5bRT6Aws_xnAnlAFg");
        //  config.put("secure", true);
        MediaManager.init(this, config);
    }
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context = SplashScreenActivity.this;
    String idToken = "";
    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//        mUser.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
//            @Override
//            public void onComplete(@NonNull Task<GetTokenResult> task) {
//                Log.d("something", "valled");
//                if (task.isSuccessful()) {
//                    idToken = task.getResult().getToken();
//                    Log.e("NoToken",idToken);
//                    sharedPreferences = context.getSharedPreferences("Token", MODE_PRIVATE);
//                    editor = sharedPreferences.edit();
//                    editor.putString("idToken", idToken);
//
//
//                    editor.commit();
//                    MemesManiaVolleyHelper M1 = new MemesManiaVolleyHelper(SplashScreenActivity.this);
//                  //  M1.createMeme("Rohit Yadav", "http://res.cloudinary.com/dfinmhios/image/upload/v1646543091/erdbf1frm6guzqthdy9j.jpg", "hello", "location", idToken);
//
//                } else {
//                    task.getException();
//                    Log.e("String Exception", String.valueOf(task.getException()));
//                }
//
//            }
//        });


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