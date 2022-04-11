package com.nith.nimbus2k22.screens.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nith.nimbus2k22.Models.Departments;
import com.nith.nimbus2k22.Models.TeamList;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.screens.teams.TeamDetail;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder> {
    private OnItemClickListener mListener;
    private  Context context;
    private List<TeamList> teamModelList;
    RequestOptions requestOptions;


    public TeamAdapter(List<TeamList> teamModelList, Context context){
        this.teamModelList=teamModelList;
        this.context=context;
        requestOptions=new RequestOptions().centerCrop().placeholder(R.drawable.refresh).error(R.drawable.refresh);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.item_team,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TeamList teamModel=teamModelList.get(position);
        holder.teamName.setText(teamModel.getName());
        holder.teamImage.setImageURI(Uri.parse(teamModel.getImage()));
        Glide.with(context).load(teamModel.getImage()).into(holder.teamImage);

        holder.itemCard.setOnClickListener(v -> {
            Intent i = new Intent(context, TeamDetail.class);
            i.putExtra("team_name",teamModel.getName());
            Log.d("GHECK_TEAM_NAME", teamModel.getName());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return teamModelList.size();
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setItemOnClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView teamName;
        ImageView teamImage;
        private ConstraintLayout itemCard;
        public MyViewHolder(@NonNull View view) {
            super(view);
            teamName=view.findViewById(R.id.teamname);
            teamImage=view.findViewById(R.id.teamimage);
            itemCard=view.findViewById(R.id.team_item_card);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
