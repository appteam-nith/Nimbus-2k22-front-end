package com.nith.nimbus2k22.screens.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.memeComment;
import com.nith.nimbus2k22.screens.models.CommentModel;
import com.nith.nimbus2k22.screens.models.MemeManiaModel;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter <CommentAdapter.MyViewHolder>{
    private Context context;
    private List<CommentModel> commentList = new ArrayList<>();
    RequestOptions requestOptions3;

    public  CommentAdapter(List<CommentModel> commentList, Context context) {
        this.context = context;
        this.commentList = commentList;
        requestOptions3 = new RequestOptions().centerCrop().placeholder(R.drawable.refresh).error(R.drawable.refresh);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.item_meme_comment,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CommentModel commentModel=commentList.get(position);
        holder.username.setText(commentModel.getUsername());
        holder.new_comment.setText(commentModel.getNew_comment());
        holder.userimage_comment.setImageURI(Uri.parse(commentModel.getUserimage_comment()));
        holder.comment.setText(commentModel.getComment());
        holder.userimage.setImageURI(Uri.parse(commentModel.getUserimage()));
        holder.usernameComment.setText(commentModel.getUsernameComment());
        Glide.with(context).load(commentModel.getUserimage_comment().replace("http","https")).apply(requestOptions3).into(holder.userimage_comment);
        Glide.with(context).load(commentModel.getUserimage().replace("http","https")).apply(requestOptions3).into(holder.userimage);
    }



    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView usernameComment;
        EditText comment;
        TextView new_comment;
        ImageView userimage;
        ImageView userimage_comment;
        public MyViewHolder( View itemView) {
            super(itemView);
            userimage= this.itemView.findViewById(R.id.userimage1);
            username= this.itemView.findViewById(R.id.usrname);
            comment= this.itemView.findViewById(R.id.comment);
            userimage_comment= this.itemView.findViewById(R.id.userimagecomment);
            usernameComment= this.itemView.findViewById(R.id.username1);
        }
    }
}
