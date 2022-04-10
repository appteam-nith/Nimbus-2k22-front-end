package com.nith.nimbus2k22;

import static com.nith.nimbus2k22.apis.EventsVolleyHelper.eventslist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.Models.EventList;
import com.nith.nimbus2k22.Models.FlappyBirdScore;
import com.nith.nimbus2k22.adapters.LeaderboardAdapter;
import com.nith.nimbus2k22.adapters.WorkshopFragAdapter;
import com.nith.nimbus2k22.apis.EventsVolleyHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link leaderboard_quiz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class leaderboard_quiz extends Fragment {


    private RecyclerView leaderBoardRV;

    private LeaderboardAdapter leaderboardAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public leaderboard_quiz() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment leaderboard_quiz.
     */
    // TODO: Rename and change types and number of parameters
    public static leaderboard_quiz newInstance(String param1, String param2) {
        leaderboard_quiz fragment = new leaderboard_quiz();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leaderboard_quiz, container, false);

        leaderBoardRV=view.findViewById(R.id.leaderboard_Rv);

//        getLeaderBoardData();




        return  view;
    }

}