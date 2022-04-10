package com.nith.nimbus2k22;

import static com.nith.nimbus2k22.apis.MemesManiaVolleyHelper.commentlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.nith.nimbus2k22.Models.CommentList;
import com.nith.nimbus2k22.apis.MemesManiaVolleyHelper;
import com.nith.nimbus2k22.screens.adapters.CommentAdapter;
import java.util.ArrayList;
import java.util.List;

public class memeComment extends AppCompatActivity {
    private static final String TAG = "hello";
    private List<CommentList> commentModels = new ArrayList<>();
    private static final String EXTRA_USERNAME = "MemeComment";
    private static final String EXTRA_IMAGE ="MemeMania" ;
    ImageView img;
    ImageView img2;
    ToggleButton togglebtn;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_comment);
        RecyclerView recyclerView = findViewById(R.id.recyView_comment);
        Intent intent = getIntent();
        String username = intent.getStringExtra(EXTRA_USERNAME);
//        String userimage=intent.getStringExtra(EXTRA_IMAGE);
//        ImageView userimage1=findViewById(R.id.userimage);
//        ImageView memeimage=findViewById(R.id.meme_image);
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