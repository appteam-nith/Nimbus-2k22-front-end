package com.nith.nimbus2k22.screens.sigma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.huawei.multimedia.audiokit.utils.Constant;
import com.nith.nimbus2k22.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VideoCallJoining extends AppCompatActivity {
    // Here the main url of the app to addedre
    private String Url ="http://appteam.monuk7735.cf";
    Button videoJoin;
    TextView remainingT;
    CountDownTimer timer;
    RequestQueue requestQueue;
    private String uid;
    String channel;
    String token;
    boolean gotDetails = false;
    private SharedPreferences sharedPref;
    ProgressBar progressBar;
    private Handler handler;
    private Runnable runnable;
    private static final String[] REQUESTED_PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int PERMISSION_REQ_ID = 22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_joining);
        progressBar = findViewById(R.id.pb);

        Log.e("onCreate", "reached");
        //handler sets the event that to be handled in future
        handler = new Handler();
        //runnable to this thread   after some interval of time
        runnable = new Runnable() {
            @Override
            public void run() {
                getChannelNameAndTokenId();
            }
        };

        remainingT = findViewById(R.id.remaining_time);
        //count down timer , after this many seconds the user should know tha we are not able to find user so he should try again
//        timer = new CountDownTimer(60000,1000) {
//            @SuppressLint("SetTextI18n")
//            @Override
//            // it after count down inteval tick should make changes
//            public void onTick(long millisUntilFinished) {
//                remainingT.setText("Retry After : "+millisUntilFinished/1000 + "s");
//                if (remainingT.getText().toString().equals("0")){
//                    timer.onFinish();
//                }
//            }
//            //afte time is finished the video button is enabled , so that user can try again
//            @Override
//            public void onFinish() {
//                videoJoin.setEnabled(true);
//                videoJoin.setText("Video");
//                handler.removeCallbacks(runnable);
//                progressBar.setVisibility(View.GONE);
//                remainingT.setText("Please Try Again");
//                Toast.makeText(getApplicationContext(), "No connection present Try Again!", Toast.LENGTH_SHORT).show();
//            }
//        };

        videoJoin = findViewById(R.id.video_join);

        videoJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("SATYAM_DEBUG", "onClick reached");
//                if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
//                        checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID) &&
//                        checkSelfPermission(REQUESTED_PERMISSIONS[2], PERMISSION_REQ_ID)) {
//                    Log.e("onclick if", "reached");
//                    progressBar.setVisibility(View.VISIBLE);
//                    videoJoin.setText("Searching the User...");
//                    videoJoin.setEnabled(false);
//                    timer.start();
//                    getUserId();
//               }
                progressBar.setVisibility(View.VISIBLE);
                    videoJoin.setText("Searching the User...");
                    videoJoin.setEnabled(false);
                    timer.start();
                    getUserId();
            }
        });

        // this whole process is to report particular user



//        videoJoin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Log.e("onclick", "reached");
//                if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
//                        checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID) &&
//                        checkSelfPermission(REQUESTED_PERMISSIONS[2], PERMISSION_REQ_ID)) {
//                    Log.e("onclick if", "reached");
//                    progressBar.setVisibility(View.VISIBLE);
//                    videoJoin.setText("Searching the User...");
//                    videoJoin.setEnabled(false);
//                    timer.start();
//                    getUserId();
//               }
//                progressBar.setVisibility(View.VISIBLE);
//                    videoJoin.setText("Searching the User...");
//                    videoJoin.setEnabled(false);
//                    timer.start();
//                    getUserId();
//
//            }
//        });
    }

    // this will get  firbase uid from shared prefrences that made durin sign up processs
    private void getUserId() {
        Log.e("getUserId", "reached");
        //sharedPref = getSharedPreferences("app", MODE_PRIVATE);
       //uid = sharedPref.getString("firebaseUid","");
       // Log.e("UID", "before");
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //uid = user.getUid();
        uid ="YjoCiuanixTpTQhFld3cWgL40xm2";
        Log.e("UID", "after");

        if (!uid.isEmpty()) {
            Log.e("UID", uid);
            getChannelNameAndTokenId();
        } else {
            Log.e("UID", "Hello");
            Toast toast = Toast.makeText(this,"Try reinstalling the app or clearing data", Toast.LENGTH_SHORT);
            toast.show();
            progressBar.setVisibility(View.GONE);
            videoJoin.setText("Video");
        }
    }
    // this method is for getting channel name and token so that user can hop on channel using  agoira
    private void getChannelNameAndTokenId() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Url + "/omegle_clone/joinvc/" + uid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("video call response", response);
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("Message"))
                    {
                        //it when we trying to find some other user  after one sec
                        Log.e("response", jsonObject.get("Message").toString());
                        handler.postDelayed(runnable,1000);                 // --for triggering getChannelNameAndTokenId() to send request to backend after every 1 sec
                    }
                    else if (jsonObject.has("uid"))
                    {
                        Log.e("onResponse: ", "got channel and token");
                        channel = jsonObject.getString("channel");
                        token = jsonObject.getString("token");
                        if (channel != null && channel.length() != 0 && token != null && token.length() != 0) {
                            gotDetails = true;
                            Intent intent = new Intent(VideoCallJoining.this, VideoCall.class);
                            intent.putExtra("channel", channel);
                            intent.putExtra("token", token);
                            intent.putExtra("uid",uid);
                            intent.putExtra("uid2",jsonObject.getString("uid2"));
                            Log.e("channel", channel);
                            Log.e("token", token);
                            handler.removeCallbacks(runnable);
                            progressBar.setVisibility(View.GONE);
                            startActivity(intent);
                            timer.cancel();
                            finish();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse: ", error.toString());
                progressBar.setVisibility(View.GONE);
                videoJoin.setText("Video");
            }
        });
        requestQueue.add(stringRequest);
    }
// don't know right knpw
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(runnable);
        finish();
    }
    // to check if user have given us permission to access android resources like net , camer etc
    private boolean checkSelfPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, REQUESTED_PERMISSIONS, requestCode);
            return false;
        }

        return true;
    }

    // sir have given note only continue after this method if all permission are given
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_ID) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED ||
                    grantResults[1] != PackageManager.PERMISSION_GRANTED ||
                    grantResults[2] != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT
                        > Build.VERSION_CODES.Q) {
                    new AlertDialog.Builder(VideoCallJoining.this)
                            .setTitle("Allow Permissions from the Settings")
                            .setMessage("1. Open Settings app.\n2. Tap Apps & notifications.\n3. Tap on Nimbus2K21. If you can't find it, first tap See all apps or App info.\n" +
                                    "4. Tap Permissions.\n" +
                                    "5. If you allowed or denied any permissions for the app, you’ll find them here.\n" +
                                    "6. To change a permission setting, tap it, then choose Allow or Deny.")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);

                                }
                            })
                            .show();
                } else {
                    new AlertDialog.Builder(VideoCallJoining.this)
                            .setTitle("Re-Ask for Permissions")
                            .setMessage("Permissions are neccesary for this feature to work properly.\n" +
                                    "Steps to follow:" +
                                    "\n1. Close the Dialog Box." +
                                    "\n2. Click on Button Again. Permissions will be asked again." +
                                    "\n3. Allow Permissions for better experience.")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }

            } else {
                progressBar.setVisibility(View.VISIBLE);
                videoJoin.setText("Searching the User...");
                videoJoin.setEnabled(false);
                timer.start();
                getUserId();
            }

            // Here we continue only if all permissions are granted.
            // The permissions can also be granted in the system settings manually.

        }

    }
}