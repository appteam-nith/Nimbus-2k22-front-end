package com.nith.nimbus2k22.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.daisy.flappybird.StartingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.Models.HomeSilderItem;
import com.nith.nimbus2k22.screens.bestTeam.BestTeamFragment;
import com.nith.nimbus2k22.screens.home.MemeManiaFragment;
import com.nith.nimbus2k22.screens.teams.Teams;

import java.util.List;

public class HomeImgSliderAdapter extends RecyclerView.Adapter<HomeImgSliderAdapter.SliderViewHolder> {

    private List<HomeSilderItem> sliderItems;
    private ViewPager2 imgSliderVP2;
    protected Context context;
    private FirebaseAuth auth;

    public HomeImgSliderAdapter(List<HomeSilderItem> sliderItems, ViewPager2 imgSliderVP2, Context context) {
        this.sliderItems = sliderItems;
        this.imgSliderVP2 = imgSliderVP2;
        this.context = context;
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_img_slider_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setSliderImg(sliderItems.get(position));

        holder.sliderImg.setOnClickListener(view -> {
            switch (position){
                case 0:
                    //intent
                    Intent i = new Intent(context, StartingActivity.class);
                    i.putExtra("flappyUid",auth.getUid());
                    Log.d("FLAPPYUID", auth.getUid());
                    context.startActivity(i);
                    break;
                case 1:
                    //memme mania
                   replaceFragment(new MemeManiaFragment(),view);
                    break;
                case 2:
                    //departmental
                    replaceFragment(new BestTeamFragment(),view);
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        private ImageView sliderImg;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            sliderImg=itemView.findViewById(R.id.slider_img);
        }

        void setSliderImg(HomeSilderItem homeSilderItem){
            sliderImg.setImageResource(homeSilderItem.getImage());
        }
    }

    private void replaceFragment(Fragment fragment, View view){

        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame_layout, fragment).addToBackStack(null).commit();

    }
}
