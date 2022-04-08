package com.nith.nimbus2k22.screens.teams;



import static com.nith.nimbus2k22.apis.CoreTeamVolleyHelper.teamlist;
import static com.nith.nimbus2k22.apis.DepartmentsVolleyHelper.DepartmentList;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nith.nimbus2k22.Models.Departments;
import com.nith.nimbus2k22.Models.TeamList;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.apis.CoreTeamVolleyHelper;
import com.nith.nimbus2k22.apis.DepartmentsVolleyHelper;
import com.nith.nimbus2k22.screens.adapters.TeamAdapter;
import java.util.ArrayList;
import java.util.List;

public class Teams extends Fragment implements TeamAdapter.OnItemClickListener {
    public static final String EXTRA_TEAM_NAME = "Team_Name";
    private final List<Departments> teamList = new ArrayList<>();
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

            View view = inflater.inflate(R.layout.fragment_teams, container, false);
            RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
//            TeamAdapter teamAdapter = new TeamAdapter(teamList, getContext());
//            recyclerView.setAdapter(teamAdapter);
//            teamAdapter.setItemOnClickListener(Teams.this);
//            StaggeredGridLayoutManager gridLayoutManager =
//                    new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//            recyclerView.setLayoutManager(gridLayoutManager);
       DepartmentsVolleyHelper Ct1 = new DepartmentsVolleyHelper(getActivity());
       Ct1.getDepartments();
       final androidx.lifecycle.Observer<List<Departments>> listObserver1 = new androidx.lifecycle.Observer<List<Departments>>() {
           @Override
           public void onChanged(List<Departments> departments) {

               TeamAdapter teamAdapter = new TeamAdapter(departments, getContext());
               recyclerView.setAdapter(teamAdapter);
               Log.e("abcd",String.valueOf(departments.get(0).getName()));
               teamAdapter.setItemOnClickListener(Teams.this);
               StaggeredGridLayoutManager gridLayoutManager =
                       new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
               recyclerView.setLayoutManager(gridLayoutManager);
           }
       };
        DepartmentList.observe(getActivity(),  listObserver1);
        return view;
        }
    @Override
    public void onItemClick(int position) {
        teamList.get(position);
        Intent intent = new Intent(getActivity(), TeamDetail.class);
        // put team name in the intent as extra
        intent.putExtra(EXTRA_TEAM_NAME, teamList.get(position).getImage());
        startActivity(intent);
    }
}
