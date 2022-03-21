package com.nith.nimbus2k22.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.modals.EventsModal;
import com.nith.nimbus2k22.modals.SponsorsModal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SponsorsAdapter extends RecyclerView.Adapter<SponsorsAdapter.SponsorsViewHolder> {

    private ArrayList<SponsorsModal> sponsorsModalArrayList;
    private Context context;

    public SponsorsAdapter(ArrayList<SponsorsModal> sponsorsModalArrayList, Context context) {
        this.sponsorsModalArrayList = sponsorsModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SponsorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sponsors, parent, false);
        return new SponsorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SponsorsViewHolder holder, int position) {
        SponsorsModal sponsor = sponsorsModalArrayList.get(position);
        if (sponsor.getImage().isEmpty()) {
//            Picasso.get().load(imgUrl.replace("http", "https")).into(holder.sponsorImage);
        } else {
            Picasso.get().load(sponsor.getImage()).into(holder.sponsorImage);
        }
        holder.sponsorDes.setText(sponsor.getName());
    }

    @Override
    public int getItemCount() {
        return sponsorsModalArrayList.size();
    }

    public class SponsorsViewHolder extends RecyclerView.ViewHolder {

        private ImageView sponsorImage;
        private TextView sponsorDes;

        public SponsorsViewHolder(@NonNull View itemView) {
            super(itemView);

            sponsorImage = itemView.findViewById(R.id.sponsor_IV);
            sponsorDes = itemView.findViewById(R.id.sponsor_des);
        }
    }
}
