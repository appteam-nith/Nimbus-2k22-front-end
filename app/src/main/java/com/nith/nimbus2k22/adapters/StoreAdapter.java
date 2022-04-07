package com.nith.nimbus2k22.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.nimbus2k22.Models.StoreList;
import com.nith.nimbus2k22.R;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private Context context;
    private ArrayList<StoreList> storeListArrayList;

    public StoreAdapter(Context context, ArrayList<StoreList> storeListArrayList) {
        this.context = context;
        this.storeListArrayList = storeListArrayList;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        view = layoutInflater.inflate(R.layout.list_item_store, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {

        StoreList storeItem = storeListArrayList.get(position);
//        holder.storeDetails.setText(storeItem.getDescription());
//        holder.storePrice.setText(storeItem.getPrice());
    }

    @Override
    public int getItemCount() {
        return storeListArrayList.size();
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder {
        private ImageView storeCard;
        private TextView storeDetails, storePrice,StoreContact;
        public StoreViewHolder(@NonNull View itemView) {

            super(itemView);
            storeCard=itemView.findViewById(R.id.store_card);
            storeDetails=itemView.findViewById(R.id.store_details_TV);
            storePrice=itemView.findViewById(R.id.store_price_TV);
            StoreContact=itemView.findViewById(R.id.store_contact_TV);
        }
    }
}
