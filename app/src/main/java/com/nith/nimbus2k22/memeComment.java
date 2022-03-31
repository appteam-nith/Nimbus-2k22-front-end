package com.nith.nimbus2k22;

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
    private static final String EXTRA_USERNAME ="username" ;
    private static final String TAG ="MemeComment" ;
    private final List<CommentModel>commentModel=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_comment);
        RecyclerView recyclerView=findViewById(R.id.recyView);
        Intent intent=getIntent();
        String username = intent.getStringExtra(EXTRA_USERNAME);
        TextView textusername = findViewById(R.id.usrname);
        textusername.setText(username);
        CommentAdapter commentAdapter=new CommentAdapter(commentModel,this);
        recyclerView.setAdapter(commentAdapter);
        addTeamDataFromJSON1();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }
    private void addTeamDataFromJSON1() {
        try {
            String jsonDataString= readJSONDataFromFile1();
            JSONArray jsonArray= new JSONArray(jsonDataString);
            for(int i=0 ; i< jsonArray.length();++i){
                System.out.println(jsonArray.get(i).toString());
                JSONObject itemObj = jsonArray.getJSONObject(i);
                String username = itemObj.getString("username");
                String usernameComment = itemObj.getString("username");
                String  userimage=itemObj.getString("userimage");
                String userimage_comment=itemObj.getString("memeimage");
                String comment=itemObj.getString("caption");
                String  new_comment=itemObj.getString("caption");
                CommentModel commentModel1= new CommentModel(username,usernameComment,userimage,userimage_comment,comment,new_comment);
                commentModel.add(commentModel1) ;

            }
        } catch (JSONException | IOException e) {
            Log.d(TAG,"addTeamDataFromJSON:", e);
        }

    }

    private String readJSONDataFromFile1() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try {
            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.memedata);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);

    }
}