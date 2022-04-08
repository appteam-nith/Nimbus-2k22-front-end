package com.nith.nimbus2k22.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.nimbus2k22.Models.EventList;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.EventDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WorkshopFragAdapter extends RecyclerView.Adapter<WorkshopFragAdapter.WrkshpFrgVH> {

    private ArrayList<EventList> eventsModalArrayList;
    private Context context;

    public WorkshopFragAdapter(ArrayList<EventList> eventsModalArrayList, Context context) {
        this.eventsModalArrayList = eventsModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public WrkshpFrgVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        view = layoutInflater.inflate(R.layout.list_event_workshops, parent, false);
        return new WrkshpFrgVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WrkshpFrgVH holder, int position) {
        EventList event = eventsModalArrayList.get(position);
        holder.eventName.setText(event.getName());
        holder.eventDetail.setText(event.getShortDescription());
        holder.regBtn.setOnClickListener(view -> {
            //reg url
//            Glide.with(context).load("https:media.geeksforgeeks.org/img-practice/banner/fork-cpp-thumbnail.png").into(eventViewHolder.eventImg);

        });
        holder.eventCard.setOnClickListener(view -> {
            changeFragment(new EventDetailsFragment(), view, event);

        });
        if (event.getImage().isEmpty()){
//            Picasso.get().load(imgUrl.replace("http", "https")).into(holder.sponsorImage);
        }else {
            Picasso.get().load("https://api.festnimbus.com/"+event.getImage().replace("http", "https")).into(holder.eventImg);
        }
    }

    @Override
    public int getItemCount() {
        return eventsModalArrayList.size();
    }

    public class WrkshpFrgVH extends RecyclerView.ViewHolder {

        private CardView eventCard;
        private ImageView eventImg;
        private TextView eventName, eventDetail, regBtn;

        public WrkshpFrgVH(@NonNull View itemView) {
            super(itemView);

            eventCard = itemView.findViewById(R.id.trans);
            eventImg = itemView.findViewById(R.id.event_item_img);
            eventName = itemView.findViewById(R.id.Event_name);
            eventDetail = itemView.findViewById(R.id.event_detail);
            regBtn = itemView.findViewById(R.id.eventRegUrl);
        }
    }

    private void changeFragment(Fragment fragment, View view, EventList events) {
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame_layout, fragment).addToBackStack(null).commit();

        Bundle bundle = new Bundle();
        bundle.putString("Title", events.getName());
        bundle.putString("ImageUrl", events.getImage());
        bundle.putString("description", events.getShortDescription());
        bundle.putString("regUrl", events.getRegistrationUrl());
        fragment.setArguments(bundle);
    }
}
