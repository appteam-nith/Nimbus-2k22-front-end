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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.modals.EventsModal;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.EventDetailsFragment;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<EventsModal> eventsModalArrayList;
    private Context context;
    private boolean isUsedInHome;

    public EventsAdapter(ArrayList<EventsModal> eventsModalArrayList, Context context, boolean isUsedInHome) {
        this.eventsModalArrayList = eventsModalArrayList;
        this.context = context;
        this.isUsedInHome = isUsedInHome;
    }

    //    @Override
//    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
//        EventsModal event = eventsModalArrayList.get(position);
//        holder.eventName.setText(event.getTitle());
//        holder.eventDetail.setText(event.getDescription());
//        holder.regBtn.setOnClickListener(view -> {
//            //reg url
//        });
//        holder.item_CV.setOnClickListener(view -> {
//            changeFragment(new EventDetailsFragment(), view, event);
//
//        });
//    }

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
    public int getItemViewType(int position) {

        if (isUsedInHome) {
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        if (viewType == 0){
            view = layoutInflater.inflate(R.layout.list_item_event,parent,false);
            return new HomeEventViewHolder(view);
        }
        view = layoutInflater.inflate(R.layout.list_event_workshops,parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EventsModal event = eventsModalArrayList.get(position);
        if (isUsedInHome){
            HomeEventViewHolder homeEventViewHolder = (HomeEventViewHolder) holder;
            homeEventViewHolder.homeEventName.setText(event.getTitle());
            //load img
            homeEventViewHolder.homeEventCard.setOnClickListener(view -> {
                changeFragment(new EventDetailsFragment(), view, event);
            });
        }
        else {
            EventViewHolder eventViewHolder = (EventViewHolder) holder;
            eventViewHolder.eventName.setText(event.getTitle());
        eventViewHolder.eventDetail.setText(event.getDescription());
        eventViewHolder.regBtn.setOnClickListener(view -> {
            //reg url
        });
        eventViewHolder.eventCard.setOnClickListener(view -> {
            changeFragment(new EventDetailsFragment(), view, event);

        });
        //load Img
        }
    }

    @Override
    public int getItemCount() {
        return eventsModalArrayList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{

        private CardView eventCard;
        private ImageView eventImg;
        private TextView eventName, eventDetail,regBtn;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            eventCard = itemView.findViewById(R.id.trans);
            eventImg = itemView.findViewById(R.id.imageView);
            eventName = itemView.findViewById(R.id.Event_name);
            eventDetail = itemView.findViewById(R.id.event_detail);
            regBtn = itemView.findViewById(R.id.eventRegUrl);




        }
    }

    class HomeEventViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout homeEventCard;
        private ImageView homeEventImg;
        private TextView homeEventName;

        public HomeEventViewHolder(@NonNull View itemView) {
            super(itemView);
            homeEventCard = itemView.findViewById(R.id.home_event_item);
            homeEventImg = itemView.findViewById(R.id.home_event_img);
            homeEventName = itemView.findViewById(R.id.home_event_name);
        }
    }
}
