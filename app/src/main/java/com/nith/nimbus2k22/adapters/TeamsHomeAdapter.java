package com.nith.nimbus2k22.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nith.nimbus2k22.Models.EventList;
import com.nith.nimbus2k22.Models.Sponsors;
import com.nith.nimbus2k22.Models.TeamList;
import com.nith.nimbus2k22.R;

import java.util.ArrayList;

public class TeamsHomeAdapter extends RecyclerView.Adapter<TeamsHomeAdapter.TeamsHomeVH> {

    private ArrayList<TeamList> teamListArrayList;
    private Context context;

    public TeamsHomeAdapter(ArrayList<TeamList> teamListArrayList, Context context) {
        this.teamListArrayList = teamListArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeamsHomeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        view = layoutInflater.inflate(R.layout.list_item_teams_home, parent, false);
        return new TeamsHomeAdapter.TeamsHomeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsHomeVH holder, int position) {

        TeamList teamMember = teamListArrayList.get(position);

        holder.homeTeamName.setText(teamMember.getName());
        Log.d("TEAM_IMG", teamMember.getImage());
        Glide.with(context).load(teamMember.getImage()).into(holder.homeTeamImg);

    }

    @Override
    public int getItemCount() {
        return teamListArrayList.size();
    }

    public class TeamsHomeVH extends RecyclerView.ViewHolder {

        private ImageView homeTeamImg;
        private TextView homeTeamName;

        public TeamsHomeVH(@NonNull View itemView) {
            super(itemView);

            homeTeamImg = itemView.findViewById(R.id.home_team_img);
            homeTeamName = itemView.findViewById(R.id.home_team_name);
        }
    }
}
