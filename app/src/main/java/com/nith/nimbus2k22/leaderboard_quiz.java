package com.nith.nimbus2k22;
import static com.nith.nimbus2k22.apis.EventsVolleyHelper.eventslist;
import static com.nith.nimbus2k22.apis.FlappyBirdVolleyHelper.flappyscore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.Models.FlappyBirdScore;
import com.nith.nimbus2k22.adapters.LeaderboardAdapter;
import com.nith.nimbus2k22.apis.FlappyBirdVolleyHelper;

import java.util.ArrayList;

public class leaderboard_quiz extends Fragment {


    private RecyclerView leaderBoardRV;

    private LeaderboardAdapter leaderboardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard_quiz, container, false);

        leaderBoardRV=view.findViewById(R.id.leaderboard_Rv);

        getLeaderBoardData();




        return  view;
    }

    private void getLeaderBoardData() {

        FlappyBirdVolleyHelper flappyBirdVolleyHelper = new FlappyBirdVolleyHelper(getContext());
        flappyBirdVolleyHelper.getFlappyBirdScore();
        final androidx.lifecycle.Observer<ArrayList<FlappyBirdScore>> observer = new androidx.lifecycle.Observer<ArrayList<FlappyBirdScore>>() {
            @Override
            public void onChanged(ArrayList<FlappyBirdScore> flappyBird_Scores) {


                leaderboardAdapter = new LeaderboardAdapter(flappyBird_Scores, getActivity());

                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                leaderBoardRV.setHasFixedSize(true);

                leaderBoardRV.setLayoutManager(manager);

                leaderBoardRV.setAdapter(leaderboardAdapter);
            }
        };
        flappyscore.observe(getActivity(), observer);
    }
        
    }

