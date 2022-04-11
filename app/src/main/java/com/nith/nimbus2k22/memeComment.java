package com.nith.nimbus2k22;

import static com.nith.nimbus2k22.apis.MemesManiaVolleyHelper.commentlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.nith.nimbus2k22.Models.CommentList;
import com.nith.nimbus2k22.Models.Memes;
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
    EditText ET1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_comment);
        RecyclerView recyclerView = findViewById(R.id.recyView_comment);
        Intent intent = getIntent();
        Memes M =(Memes)intent.getSerializableExtra("Memes");
//        String username = intent.getStringExtra(EXTRA_USERNAME);
//        String userimage=intent.getStringExtra(EXTRA_IMAGE);
        ImageView userimage1=findViewById(R.id.imag);
        ImageView usermg = findViewById(R.id.userimage1);
        TextView textusername = findViewById(R.id.usrname);
        TextView caption = findViewById(R.id.caption);
        textusername.setText(M.getAutohr());
        caption.setText(M.getText());
        Log.e("helloComment",String.valueOf(M.getPhoto()));
        String abc = M.getPhoto();
       Glide.with(getApplicationContext()).load(M.getPhoto().replace("http","https")).into(usermg);

          Glide.with(getApplicationContext()).load(M.getPhoto().replace("http","httpsl̥")).into(userimage1);
//        Glide.with(getApplicationContext()).load(M.getPhoto()).into(usermg);
      img = findViewById(R.id.commentSend);
      ET1 = findViewById(R.id.comment);
      img.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String Text = ET1.getText().toString();
             MemesManiaVolleyHelper M4 = new MemesManiaVolleyHelper(memeComment.this);
             M4.commentCreate(M.getId(),M.getAutohr(),Text);

          }
      });

        MemesManiaVolleyHelper c4 = new MemesManiaVolleyHelper(memeComment.this);
      c4.getCommentList(M.getId());
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