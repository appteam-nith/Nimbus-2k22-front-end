package com.nith.nimbus2k22.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nith.nimbus2k22.Models.Sponsors;
import com.nith.nimbus2k22.R;

import java.util.ArrayList;

public class SponsorsHomeAdapter extends RecyclerView.Adapter<SponsorsHomeAdapter.HomeSponViewHolder> {

    private ArrayList<Sponsors> sponsorsModalArrayList;
    private Context context;

    public SponsorsHomeAdapter(ArrayList<Sponsors> sponsorsModalArrayList, Context context) {
        this.sponsorsModalArrayList = sponsorsModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeSponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        view = layoutInflater.inflate(R.layout.list_item_sponsors_home, parent, false);
        return new HomeSponViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSponViewHolder holder, int position) {
        Sponsors sponsor = sponsorsModalArrayList.get(position);
        holder.homeSponsorName.setText(sponsor.getName());
        //load img
        Glide.with(context).load("https://api.festnimbus.com/" + sponsor.getImage()).into(holder.homeSponsorImg);

        //on clicklistener??
    }

    @Override
    public int getItemCount() {
        return sponsorsModalArrayList.size();
    }

    public class HomeSponViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout homeSponsorItem;
        private ImageView homeSponsorImg;
        private TextView homeSponsorName;

        public HomeSponViewHolder(@NonNull View itemView) {
            super(itemView);
            homeSponsorItem = itemView.findViewById(R.id.home_sponsor_item);
            homeSponsorImg = itemView.findViewById(R.id.home_sponsor_img);
            homeSponsorName = itemView.findViewById(R.id.home_sponsor_name);
        }
    }
}
