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

import com.bumptech.glide.Glide;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.modals.EventsModal;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.EventDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<EventsModal> eventsModalArrayList;
    private Context context;
    private boolean isUsedInHomeEvent;
    private boolean isUsedInHomeWorkshop;

    public EventsAdapter(ArrayList<EventsModal> eventsModalArrayList, Context context, boolean isUsedInHomeEvent, boolean isUsedInHomeWorkshop) {
        this.eventsModalArrayList = eventsModalArrayList;
        this.context = context;
        this.isUsedInHomeEvent = isUsedInHomeEvent;
        this.isUsedInHomeWorkshop = isUsedInHomeWorkshop;
    }


    private void changeFragment(Fragment fragment, View view, EventsModal events) {
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
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

        if (isUsedInHomeEvent) {
            return 0;
        }
        if(isUsedInHomeWorkshop) {
            return 1;
        }
        return 2;
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
        if (viewType == 1){
            view = layoutInflater.inflate(R.layout.list_workshops_home,parent,false);
            return new HomeWorkshopViewHolder(view);
        }
        view = layoutInflater.inflate(R.layout.list_event_workshops,parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EventsModal event = eventsModalArrayList.get(position);
        if (isUsedInHomeEvent){
            HomeEventViewHolder homeEventViewHolder = (HomeEventViewHolder) holder;
            homeEventViewHolder.homeEventName.setText(event.getTitle());
            //load img
            Picasso.get().load("https://media.geeksforgeeks.org/img-practice/banner/fork-cpp-thumbnail.png").into(homeEventViewHolder.homeEventImg);
            homeEventViewHolder.homeEventCard.setOnClickListener(view -> {
                changeFragment(new EventDetailsFragment(), view, event);
            });
        }else if (isUsedInHomeWorkshop){
            HomeWorkshopViewHolder homeWorkshopViewHolder = (HomeWorkshopViewHolder) holder;
            homeWorkshopViewHolder.homeWorkshopName.setText(event.getTitle());
            //load img;
            homeWorkshopViewHolder.homeWorkshopItem.setOnClickListener(view -> {
                changeFragment(new EventDetailsFragment(), view, event);
            });
        }
        else {
            EventViewHolder eventViewHolder = (EventViewHolder) holder;
            eventViewHolder.eventName.setText(event.getTitle());
        eventViewHolder.eventDetail.setText(event.getDescription());
        eventViewHolder.regBtn.setOnClickListener(view -> {
            //reg url
//            Glide.with(context).load("https:media.geeksforgeeks.org/img-practice/banner/fork-cpp-thumbnail.png").into(eventViewHolder.eventImg);

        });
        eventViewHolder.eventCard.setOnClickListener(view -> {
            changeFragment(new EventDetailsFragment(), view, event);

        });
        //load Img
            Picasso.get().load("https://media.geeksforgeeks.org/img-practice/banner/fork-cpp-thumbnail.png").into(eventViewHolder.eventImg);
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
            eventImg = itemView.findViewById(R.id.event_item_img);
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

    class HomeWorkshopViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout homeWorkshopItem;
        private ImageView homeWorkshopImg;
        private TextView homeWorkshopName;
        public HomeWorkshopViewHolder(@NonNull View itemView) {
            super(itemView);

            homeWorkshopItem =itemView.findViewById(R.id.home_workshop_item);
            homeWorkshopImg =itemView.findViewById(R.id.home_workshop_img);
            homeWorkshopName =itemView.findViewById(R.id.home_workshop_name);

        }
    }
}
