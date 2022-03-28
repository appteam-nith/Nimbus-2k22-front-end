package com.nith.nimbus2k22.screens.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.screens.models.TeamModel;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder> {
    private OnItemClickListener mListener;
    private  Context context;
    private List<TeamModel> teamModelList;
    RequestOptions requestOptions;


    public TeamAdapter(List<TeamModel> teamModelList,Context context){
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
        TeamModel teamModel=teamModelList.get(position);
        holder.teamName.setText(teamModel.getTeam_name());
        holder.teamImage.setImageURI(Uri.parse(teamModel.getTeam_image()));
        Glide.with(context).load(teamModel.getTeam_image().replace("http","https")).apply(requestOptions).into(holder.teamImage);
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
        public MyViewHolder(@NonNull View view) {
            super(view);
            teamName=view.findViewById(R.id.teamname);
            teamImage=view.findViewById(R.id.teamimage);
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
