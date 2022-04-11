package com.nith.nimbus2k22.screens.bestTeam;

import static com.nith.nimbus2k22.apis.CoreTeamVolleyHelper.teamlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.Models.TeamList;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.adapters.BestTeamAdapter;
import com.nith.nimbus2k22.adapters.TeamsHomeAdapter;
import com.nith.nimbus2k22.apis.CoreTeamVolleyHelper;

import java.util.ArrayList;

public class BestTeamFragment extends Fragment {


    RecyclerView bestTeamRV;
    BestTeamAdapter teamnAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_best_team, container, false);

        bestTeamRV=view.findViewById(R.id.best_team_RV);

        getTeamsData();



        return view;
    }

    private void getTeamsData() {

        CoreTeamVolleyHelper coreTeamVolleyHelper = new CoreTeamVolleyHelper(getContext());
        coreTeamVolleyHelper.getTeamList();
        final androidx.lifecycle.Observer<ArrayList<TeamList>> observer = new androidx.lifecycle.Observer<ArrayList<TeamList>>() {
            @Override
            public void onChanged(ArrayList<TeamList> teamLists) {




                teamnAdapter = new BestTeamAdapter(teamLists, getActivity());
                Log.d("TEAM_IMG", teamLists.get(0).getImage());

                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                bestTeamRV.setHasFixedSize(true);

                bestTeamRV.setLayoutManager(manager);

                bestTeamRV.setAdapter(teamnAdapter);
            }
        };
        teamlist.observe(requireActivity(), observer);
    }
}