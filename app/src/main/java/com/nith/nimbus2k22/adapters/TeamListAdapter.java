package com.nith.nimbus2k22.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.nimbus2k22.modals.TeamListModal;

import java.util.ArrayList;
import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.MyViewHolder> {
    private final List<TeamListModal>teamListModals=new ArrayList<>();
    

    @NonNull
    @Override
    public TeamListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamListAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
