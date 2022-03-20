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

import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.modals.EventsModal;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.EventDetailsFragment;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

    private ArrayList<EventsModal> eventsModalArrayList;
    private Context context;

    public EventsAdapter(ArrayList<EventsModal> eventsModalArrayList, Context context) {
        this.eventsModalArrayList = eventsModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_event_workshops, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventsModal event = eventsModalArrayList.get(position);
        holder.eventName.setText(event.getTitle());
        holder.eventDetail.setText(event.getDescription());
        holder.regBtn.setOnClickListener(view -> {
            //reg url
        });
        holder.item_CV.setOnClickListener(view -> {
            changeFragment(new EventDetailsFragment(), view, event);

        });
    }

    private void changeFragment(Fragment fragment, View view, EventsModal events) {
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        //TODO: change replace id to --"fragment_frame_layout"--
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame_layout, fragment).addToBackStack(null).commit();

        Bundle bundle = new Bundle();
        bundle.putString("Title", events.getTitle());
        bundle.putString("ImageUrl", events.getImage());
        bundle.putString("description", events.getDescription());
        bundle.putString("regUrl", events.getRegURL());
        fragment.setArguments(bundle);
    }

    @Override
    public int getItemCount() {
        return eventsModalArrayList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        //create variable for views
        private ImageView eventImg;
        private TextView eventName, eventDetail, regBtn;
        private CardView item_CV;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

//             initialize views with ids.
            eventName = itemView.findViewById(R.id.Event_name);
            eventDetail = itemView.findViewById(R.id.event_detail);
            regBtn = itemView.findViewById(R.id.eventRegUrl);
            item_CV = itemView.findViewById(R.id.trans);
        }
    }
}
