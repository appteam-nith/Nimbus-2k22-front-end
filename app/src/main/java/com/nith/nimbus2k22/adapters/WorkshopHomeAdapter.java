package com.nith.nimbus2k22.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.nimbus2k22.Models.EventList;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.EventDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WorkshopHomeAdapter extends RecyclerView.Adapter<WorkshopHomeAdapter.WrkshpHomeVH> {

    private ArrayList<EventList> eventsModalArrayList;
    private Context context;

    public WorkshopHomeAdapter(ArrayList<EventList> eventsModalArrayList, Context context) {
        this.eventsModalArrayList = eventsModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public WrkshpHomeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        view = layoutInflater.inflate(R.layout.list_workshops_home, parent, false);
        return new WrkshpHomeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WrkshpHomeVH holder, int position) {
        EventList event = eventsModalArrayList.get(position);
        holder.homeWorkshopName.setText(event.getName());
        //load img;
        Picasso.get().load("https://api.festnimbus.com/"+event.getImage()).into(holder.homeWorkshopImg);
        holder.homeWorkshopItem.setOnClickListener(view -> {
            changeFragment(new EventDetailsFragment(), view, event);
        });
        holder.homeWorkshopOrganizer.setText(event.getUsername().replace('_', ' ').toUpperCase());

    }

    @Override
    public int getItemCount() {
        return eventsModalArrayList.size();
    }

    public class WrkshpHomeVH extends RecyclerView.ViewHolder {

        private LinearLayout homeWorkshopItem;
        private ImageView homeWorkshopImg;
        private TextView homeWorkshopName;
        private TextView homeWorkshopOrganizer;

        public WrkshpHomeVH(@NonNull View itemView) {
            super(itemView);

            homeWorkshopItem = itemView.findViewById(R.id.home_event_item);
            homeWorkshopImg = itemView.findViewById(R.id.home_event_img);
            homeWorkshopName = itemView.findViewById(R.id.home_event_name);
            homeWorkshopOrganizer = itemView.findViewById(R.id.home_event_organizer);
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
