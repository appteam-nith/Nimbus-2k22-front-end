package com.nith.nimbus2k22.screens.teams;


import static com.nith.nimbus2k22.apis.CoreTeamVolleyHelper.newmemberlist;
import static com.nith.nimbus2k22.screens.teams.Teams.EXTRA_TEAM_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.nith.nimbus2k22.Models.TeamMemberlist;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.adapters.TeamDetailAdapter;
import com.nith.nimbus2k22.apis.CoreTeamVolleyHelper;

import java.util.ArrayList;
import java.util.List;

public class TeamDetail extends AppCompatActivity {
    public static final String EXTRA_TEAM_NAME = "Team_Name";
    public static final String TAG="TeamDetailActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        RecyclerView recyclerView=findViewById(R.id.teamsDRV);
        Intent intent=getIntent();
        String Team_Name = intent.getStringExtra("team_name");
        TextView textViewTeamName = findViewById(R.id.team_name1);
        textViewTeamName.setText(Team_Name);
//        Log.d("ITENT", Team_Name);
        CoreTeamVolleyHelper n1 = new CoreTeamVolleyHelper(TeamDetail.this);
        n1.getTeamMembers(Team_Name);
        final androidx.lifecycle.Observer<List<TeamMemberlist>> obs2 = new  androidx.lifecycle.Observer<List<TeamMemberlist>>() {
            @Override
            public void onChanged(List<TeamMemberlist> newMembersList) {
                TeamDetailAdapter teamDetailAdapter=new TeamDetailAdapter(newMembersList,getApplicationContext());
        recyclerView.setAdapter(teamDetailAdapter);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
            }
        };
        newmemberlist.observe(this,obs2);

   }
    }


