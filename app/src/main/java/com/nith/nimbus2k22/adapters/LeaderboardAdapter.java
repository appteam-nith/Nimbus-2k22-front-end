package com.nith.nimbus2k22.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.nimbus2k22.Models.FlappyBirdScore;
import com.nith.nimbus2k22.R;

import java.util.ArrayList;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LBViewHolder> {

    private ArrayList<FlappyBirdScore> scoreArrayList;
    private Context context;

    public LeaderboardAdapter(ArrayList<FlappyBirdScore> scoreArrayList, Context context) {
        this.scoreArrayList = scoreArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public LBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        view = layoutInflater.inflate(R.layout.list_item_sponsors, parent, false);
        return new LBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LBViewHolder holder, int position) {

        FlappyBirdScore flappyBirdScore = scoreArrayList.get(position);

            holder.score.setText(String.valueOf(flappyBirdScore.getTotalScore()));
    }

    @Override
    public int getItemCount() {
        return scoreArrayList.size();
    }

    public class LBViewHolder extends RecyclerView.ViewHolder {

        private TextView score;
        public LBViewHolder(@NonNull View itemView) {
            super(itemView);

            score=itemView.findViewById(R.id.score_TV);
        }
    }
}
