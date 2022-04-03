package com.nith.nimbus2k22.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nith.nimbus2k22.Models.Sponsors;
import com.nith.nimbus2k22.R;

import java.util.ArrayList;

public class SponsorsFragAdapter extends RecyclerView.Adapter<SponsorsFragAdapter.SponsorViewHolder> {

    private ArrayList<Sponsors> sponsorsModalArrayList;
    private Context context;

    public SponsorsFragAdapter(ArrayList<Sponsors> sponsorsModalArrayList, Context context) {
        this.sponsorsModalArrayList = sponsorsModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SponsorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        view = layoutInflater.inflate(R.layout.list_item_sponsors, parent, false);
        return new SponsorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SponsorViewHolder holder, int position) {
        Sponsors sponsor = sponsorsModalArrayList.get(position);

        holder.sponsorDes.setText(sponsor.getName());
        //??
        //load img
        Glide.with(context).load("https://api.festnimbus.com/"+sponsor.getImage()).into(holder.sponsorImage);

    }

    @Override
    public int getItemCount() {
        return sponsorsModalArrayList.size();
    }

    public  class SponsorViewHolder extends RecyclerView.ViewHolder{
        private ImageView sponsorImage;
        private TextView sponsorDes;
        private ConstraintLayout sponsorItem;

        public SponsorViewHolder(@NonNull View itemView) {
            super(itemView);
            sponsorImage = itemView.findViewById(R.id.sponsor_IV);
            sponsorDes = itemView.findViewById(R.id.sponsor_des);
            sponsorItem = itemView.findViewById(R.id.sponsor_item);
        }
    }
}
