package com.nith.nimbus2k22.screens.teams;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.screens.adapters.TeamAdapter;
import com.nith.nimbus2k22.screens.models.TeamModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Teams extends Fragment implements TeamAdapter.OnItemClickListener {
    public static final String EXTRA_TEAM_NAME = "Team_Name";
    private final List<TeamModel> teamList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    private static final String TAG="Team Fragment";


    public Teams() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
try {
    View view = inflater.inflate(R.layout.fragment_teams, container, false);
    RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
    TeamAdapter teamAdapter = new TeamAdapter(teamList, getContext());
    recyclerView.setAdapter(teamAdapter);
    teamAdapter.setItemOnClickListener(Teams.this);
    StaggeredGridLayoutManager gridLayoutManager =
            new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(gridLayoutManager);
    addTeamDataFromJSON();
    return view;
}catch (Exception e){
    Log.e(TAG,"Oncreateview",e);
    throw e;
}
    }

    private void addTeamDataFromJSON() {
        try {
            String jsonDataString= readJSONDataFromFile();
            JSONArray jsonArray= new JSONArray(jsonDataString);
            for(int i=0 ; i< jsonArray.length();++i){
                System.out.println(jsonArray.get(i).toString());
                JSONObject itemObj = jsonArray.getJSONObject(i);
                int id=itemObj.getInt("id");
                String team_name = itemObj.getString("Team_Name");
                String  team_image=itemObj.getString("team image");
                TeamModel teamDetailData = new TeamModel(id,team_name, team_image);
                teamList.add(teamDetailData) ;

            }
        } catch (JSONException | IOException e) {
            Log.d(TAG,"addTeamDataFromJSON:", e);
        }

    }

    private String readJSONDataFromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try {
            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.teamdata);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            while ((jsonString = bufferedReader.readLine()) != null){
                builder.append(jsonString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        } return new String(builder);
    }


    @Override
    public void onItemClick(int position) {
        teamList.get(position);
        Intent intent = new Intent(getActivity(), TeamDetail.class);
        // put team name in the intent as extra
        intent.putExtra(EXTRA_TEAM_NAME, teamList.get(position).getTeam_name());
        startActivity(intent);
    }
}