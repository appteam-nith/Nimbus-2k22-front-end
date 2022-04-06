package com.nith.nimbus2k22.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.nith.nimbus2k22.screens.eventsAndWorkshops.EventFragment;
import com.nith.nimbus2k22.screens.eventsAndWorkshops.WorkshopFragment;
import com.nith.nimbus2k22.screens.sponsors.SponsorsFragment;

public class EventWorkshopAdapter extends FragmentStateAdapter {
    public EventWorkshopAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new SponsorsFragment();
        }
        return new EventFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
