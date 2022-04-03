package com.nith.nimbus2k22;

import static com.nith.nimbus2k22.apis.MemesManiaVolleyHelper.commentlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.nith.nimbus2k22.Models.CommentList;
import com.nith.nimbus2k22.apis.MemesManiaVolleyHelper;
import com.nith.nimbus2k22.screens.adapters.CommentAdapter;
import com.nith.nimbus2k22.screens.models.CommentModel;
import com.nith.nimbus2k22.screens.models.MemeManiaModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class memeComment extends AppCompatActivity {
    private static final String TAG = "hello";
    private List<CommentModel> commentModels = new ArrayList<>();
    private static final String EXTRA_USERNAME = "MemeComment";
    ImageView img;
    ImageView img2;
    ToggleButton togglebtn;


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_comment);
        RecyclerView recyclerView = findViewById(R.id.recyView);
        Intent intent = getIntent();
        String username = intent.getStringExtra(EXTRA_USERNAME);
        TextView textusername = findViewById(R.id.usrname);
        textusername.setText(username);
        MemesManiaVolleyHelper c4 = new MemesManiaVolleyHelper(memeComment.this);
        c4.getCommentList("3");
        final androidx.lifecycle.Observer<List<CommentList>> observer2 = new androidx.lifecycle.Observer<List<CommentList>>() {
            @Override
            public void onChanged(List<CommentList> commentLists) {
                CommentAdapter commentAdapter = new CommentAdapter(commentLists, getApplicationContext());
                recyclerView.setAdapter(commentAdapter);
                // addTeamDataFromJSON1();
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        };
        commentlist.observe(this, observer2);
    }
}