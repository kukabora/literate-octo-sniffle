package com.example.donorapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.donorapplication.AppDatabase;
import com.example.donorapplication.Entity.Post;
import com.example.donorapplication.Entity.PostLike;
import com.example.donorapplication.Entity.Topic;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.R;
import com.example.donorapplication.ThreadPostActivity;
import com.example.donorapplication.Utils.AuthUtil;
import com.example.donorapplication.Utils.ImageToByteArray;

import java.util.List;

public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Post> posts;
    AppDatabase db;


    public ProfilePostAdapter(Context context, List<Post> posts) {
        this.posts = posts;
        db = AppDatabase.getInstance(context);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ProfilePostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.profile_post_preview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfilePostAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        if (post.getImage() != null){
            Bitmap bm = null;
            try {
                bm = ImageToByteArray.bytesToImage(post.getImage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.postImage.setImageBitmap(bm);
        }
        SharedPreferences preferences = inflater.getContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
        User user = AuthUtil.getUserByToken(preferences, db);
        holder.postDescription.setText(String.valueOf(post.getContent()));
        holder.postCreation.setText(String.valueOf(post.getDateOfCreation()));
        holder.likesAmount.setText(String.valueOf(post.getLikesAmount()));
        holder.likesAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!db.postLikeDAO().checkIfUserLikedPost(post.getId(), user.getId())){
                    db.postLikeDAO().insert(new PostLike(db.postLikeDAO().getAll().size(), user.getId(), post.getId()));
                    Toast toast = Toast.makeText(inflater.getContext(),
                            "Liked!", Toast.LENGTH_SHORT);
                    toast.show();
                    post.setLikesAmount(post.getLikesAmount() + 1);
                    db.postDao().update(post);
                    holder.likesAmount.setText(String.valueOf(post.getLikesAmount()));
                } else {
                    Toast toast = Toast.makeText(inflater.getContext(),
                            "You have already liked this post.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView postCreation, likesAmount, postDescription;
        final ImageView postImage;

        ViewHolder(View view){
            super(view);
            postCreation = view.findViewById(R.id.postCreationDate);
            likesAmount = view.findViewById(R.id.likesAmount);
            postDescription = view.findViewById(R.id.postDescription);
            postImage = view.findViewById(R.id.postImage);
        }
    }
}