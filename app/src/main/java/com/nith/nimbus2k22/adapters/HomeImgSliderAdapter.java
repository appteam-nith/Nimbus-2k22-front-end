package com.nith.nimbus2k22.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.modals.HomeSilderItem;

import java.util.List;

public class HomeImgSliderAdapter extends RecyclerView.Adapter<HomeImgSliderAdapter.SliderViewHolder> {

    private List<HomeSilderItem> sliderItems;
    private ViewPager2 imgSliderVP2;

    public HomeImgSliderAdapter(List<HomeSilderItem> sliderItems, ViewPager2 imgSliderVP2) {
        this.sliderItems = sliderItems;
        this.imgSliderVP2 = imgSliderVP2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_img_slider_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setSliderImg(sliderItems.get(position));
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
}
