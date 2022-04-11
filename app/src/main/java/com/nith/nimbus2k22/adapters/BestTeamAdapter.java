package com.nith.nimbus2k22.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nith.nimbus2k22.Models.Sponsors;
import com.nith.nimbus2k22.Models.TeamList;
import com.nith.nimbus2k22.R;

import java.util.ArrayList;

public class BestTeamAdapter extends RecyclerView.Adapter<BestTeamAdapter.BestTeamViewHolder> {

    private ArrayList<TeamList> teamLists;
    private Context context;

    public BestTeamAdapter(ArrayList<TeamList> teamLists, Context context) {
        this.teamLists = teamLists;
        this.context = context;
    }

    @NonNull
    @Override
    public BestTeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        view = layoutInflater.inflate(R.layout.list_item_best_team, parent, false);
        return new BestTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestTeamViewHolder holder, int position) {
        TeamList team = teamLists.get(position);
        holder.teamName.setText(team.getName());
        Glide.with(context).load("https://api.festnimbus.com/" + team.getImage()).into(holder.teamImg);

    }

    @Override
    public int getItemCount() {
        return teamLists.size();
    }

    public class BestTeamViewHolder extends RecyclerView.ViewHolder {

        ImageView teamImg;
        TextView teamName;
        public BestTeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamImg=itemView.findViewById(R.id.team_img);
            teamName=itemView.findViewById(R.id.team_name);
        }
    }
}
