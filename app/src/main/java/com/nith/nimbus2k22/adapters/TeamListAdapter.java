package com.nith.nimbus2k22.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.modals.TeamListModal;

import java.util.ArrayList;
import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.DepViewHolder> {
    private List<TeamListModal> list = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public DepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.id.,parent,false);
        return new DepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepViewHolder holder, int position) {
        TeamListModal teams=list.get(position);
        holder.teamImage.setImageURI(Uri.parse(teams.getImage()));
        holder.teamName.setText(teams.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DepViewHolder extends RecyclerView.ViewHolder {
        ImageView teamImage;
        TextView teamName;
        public DepViewHolder(@NonNull View itemView) {
            super(itemView);
            teamImage=itemView.findViewById(R.id.teamImg);
            teamName=itemView.findViewById(R.id.teamName);
        }
    }


}
