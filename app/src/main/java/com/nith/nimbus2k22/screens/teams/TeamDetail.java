package com.nith.nimbus2k22.screens.teams;


import static com.nith.nimbus2k22.apis.CoreTeamVolleyHelper.memberslist;
import static com.nith.nimbus2k22.screens.teams.Teams.EXTRA_TEAM_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.nith.nimbus2k22.Models.TeamMemberlist;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.apis.CoreTeamVolleyHelper;
import com.nith.nimbus2k22.screens.adapters.TeamDetailAdapter;

import java.util.ArrayList;
import java.util.List;

public class TeamDetail extends AppCompatActivity {
    private final List<TeamMemberlist> teamDetailModelList=new ArrayList<>();
    public static final String TAG="TeamDetailActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        RecyclerView recyclerView=findViewById(R.id.teamsDRV);
        Intent intent=new Intent();
        String Team_Name = intent.getStringExtra(EXTRA_TEAM_NAME);
        TextView textViewTeamName = findViewById(R.id.team_name1);
        textViewTeamName.setText(Team_Name);
        CoreTeamVolleyHelper n1 = new CoreTeamVolleyHelper(TeamDetail.this);
        n1.getTeamMembers("appteam");
        final androidx.lifecycle.Observer<List<TeamMemberlist>> obs2 = new  androidx.lifecycle.Observer<List<TeamMemberlist>>() {
            @Override
            public void onChanged(List<TeamMemberlist> newMembersList) {
                for (int i=0; i<newMembersList.size();i++ ){
                    teamDetailModelList.add(newMembersList.get(i));
                }
                TeamDetailAdapter teamDetailAdapter=new TeamDetailAdapter(teamDetailModelList,getApplicationContext());
        recyclerView.setAdapter(teamDetailAdapter);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
            }
        };
        memberslist.observe(this,obs2);

    }
    }


