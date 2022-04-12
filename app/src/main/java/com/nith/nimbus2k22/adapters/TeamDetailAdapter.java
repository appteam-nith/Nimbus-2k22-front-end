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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nith.nimbus2k22.Models.TeamMemberlist;
import com.nith.nimbus2k22.R;

import java.util.List;

public class TeamDetailAdapter extends RecyclerView.Adapter<TeamDetailAdapter.MyViewHolder> {
   private final Context context;
   private final List<TeamMemberlist> teamDetailModelList;
   RequestOptions options;
 public TeamDetailAdapter(List<TeamMemberlist>teamDetailModelList, Context context){
     this.context= (Context) context;
     this.teamDetailModelList=teamDetailModelList;
     options=new RequestOptions().centerCrop().placeholder(R.drawable.refresh).error(R.drawable.refresh);
 }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemDetailView = LayoutInflater.from(context).inflate(R.layout.item_teamdetail, parent, false);
        return new MyViewHolder(itemDetailView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder detailholder, int position) {
          TeamMemberlist teamDetail=teamDetailModelList.get(position);
        detailholder.teamMemImage.setImageURI(Uri.parse(teamDetail.getImage()));
        detailholder.teamMemName.setText(teamDetail.getName());
        detailholder.memDesignation.setText(teamDetail.getPosition());
        Glide.with(context).load(teamDetail.getImage().replace("http","https")).apply(options).into(detailholder.teamMemImage);

    }

    @Override
    public int getItemCount() {
        if (teamDetailModelList ==null) {

        }
        else {
            return teamDetailModelList.size();
        }
        return 0;

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        int id;
        TextView teamName;
        ImageView teamMemImage;
        TextView teamMemName;
        TextView memDesignation;

        public MyViewHolder(@NonNull View detailview) {
            super(detailview);
            id=detailview.getId();
            teamName = (TextView) detailview.findViewById(R.id.team_name1);
            teamMemImage = (ImageView) detailview.findViewById(R.id.team_member_image);
            teamMemName=(TextView) detailview.findViewById(R.id.team_member_name);
            memDesignation=(TextView) detailview.findViewById(R.id.designation);
        }
    }
}
