package com.nith.nimbus2k22.screens.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nith.nimbus2k22.Models.Memes;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.memeComment;
import com.nith.nimbus2k22.screens.models.MemeManiaModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MemeManiaAdapter extends RecyclerView.Adapter<MemeManiaAdapter.MyViewHolder> {
    private Context context;
    private OnItemClickListener mListener;
    private List<Memes> memeList=new ArrayList<>();
    RequestOptions requestOptions1;
    public MemeManiaAdapter(List<Memes> memeList, Context context){
        this.memeList=memeList;
        this.context=context;
        requestOptions1=new RequestOptions().centerCrop().placeholder(R.drawable.refresh).error(R.drawable.refresh);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.item_meme_mania,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      Memes memeManiaModel=memeList.get(position);
      holder.userimage.setImageURI(Uri.parse(memeManiaModel.getPhoto()));
      holder.caption.setText(memeManiaModel.getText());
      holder.memeImage.setImageURI(Uri.parse(memeManiaModel.getPhoto()));
      holder.username.setText((CharSequence) memeManiaModel.getAutohr());
        Glide.with(context).load(memeManiaModel.getPhoto().replace("http","https")).apply(requestOptions1).into(holder.memeImage);
        Glide.with(context).load(memeManiaModel.getPhoto().replace("http","https")).apply(requestOptions1).into(holder.userimage);
    }
    @Override
    public int getItemCount() {
        return memeList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setItemOnClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView caption;
        ImageView memeImage;
        ImageView userimage;
        ImageView fab1;
        public MyViewHolder(View view) {
            super(view);
           userimage= view.findViewById(R.id.userimage);
           username=view.findViewById(R.id.usrname);
           caption=view.findViewById(R.id.caption);
           memeImage=view.findViewById(R.id.meme_image);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
