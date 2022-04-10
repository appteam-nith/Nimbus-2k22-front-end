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

public class Teams extends Fragment  {
    public static final String EXTRA_TEAM_NAME = "Team_Name";
    private final List<TeamList> mteamList = new ArrayList<>();
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
       CoreTeamVolleyHelper Ct1 = new CoreTeamVolleyHelper(getActivity());
       Ct1.getTeamList();
       final androidx.lifecycle.Observer<List<TeamList>> listObserver1 = new androidx.lifecycle.Observer<List<TeamList>>() {
           @Override
           public void onChanged(List<TeamList> departments) {

               TeamAdapter teamAdapter = new TeamAdapter(departments, getContext());
               recyclerView.setAdapter(teamAdapter);
               for(int i=0;i<departments.size();i++) {
                   Log.e("nnn", departments.get(i).getName());
               }
//               teamAdapter.setItemOnClickListener(Teams.this);
               StaggeredGridLayoutManager gridLayoutManager =
                       new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
               recyclerView.setLayoutManager(gridLayoutManager);
               for (int i=0; i<departments.size();i++ ){
                   mteamList.add(departments.get(i));
               }
           }
       };
        teamlist.observe(getActivity(),  listObserver1);
        return view;
        }
//    @Override
//    public void onItemClick(int position) {
////        mteamList.get(position);
////        Intent intent = new Intent(requireActivity(), TeamDetail.class);
////        // put team name in the intent as extra
//////        intent.putExtra(EXTRA_TEAM_NAME, mteamList.get(position).getName());
////
////        intent.putExtra("team_name",TeamDetail.class.getName());
////        startActivity(intent);
//    }
}
