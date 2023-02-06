package com.example.donorapplication.Adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donorapplication.AppDatabase;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.R;
import com.example.donorapplication.ThreadPostActivity;
import com.example.donorapplication.Utils.ImageToByteArray;

import java.util.List;

public class ThreadPostAdapter  extends RecyclerView.Adapter<ThreadPostAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<ThreadPost> threadPosts;
    AppDatabase db;


    public ThreadPostAdapter(Context context, List<ThreadPost> threadPosts) {
        this.threadPosts = threadPosts;
        db = AppDatabase.getInstance(context);
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ThreadPostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.thread_post_preview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ThreadPostAdapter.ViewHolder holder, int position) {
        ThreadPost threadPost = threadPosts.get(position);
        User user = db.userDao().getById(threadPost.getAuthorId());
        if (user.getAvatar() != null){
            Bitmap bm = null;
            try {
                bm = ImageToByteArray.bytesToImage(user.getAvatar());
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.avatarView.setImageBitmap(bm);
        }
        else
        {
            holder.avatarView.setImageResource(R.drawable.profile_placeholder_avatar);
        }
        holder.authorName.setText(String.valueOf(user.getFirstName()) + " " + String.valueOf(user.getLastName()));
        holder.bannedAmount.setText(String.valueOf(threadPost.getBannedAmount()));
        holder.dateView.setText(String.valueOf(threadPost.getDateOfCreation()));
        holder.answeredAmount.setText(String.valueOf(threadPost.getAnswersAmount()));
        holder.thesisText.setText(String.valueOf(threadPost.getThesis()));
        holder.hiddenId.setText(String.valueOf(threadPost.getId()));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context , ThreadPostActivity.class);
                Bundle b = new Bundle();
                b.putInt("threadPostId", threadPost.getId());
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return threadPosts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ConstraintLayout mainLayout;
        final TextView answeredAmount, bannedAmount, thesisText, hiddenId, authorName, dateView;
        final ImageView avatarView;

        ViewHolder(View view){
            super(view);
            answeredAmount = view.findViewById(R.id.answersAmountView);
            bannedAmount =  view.findViewById(R.id.bannedAmount);
            thesisText = view.findViewById(R.id.thesisView);
            avatarView = view.findViewById(R.id.avatarImageView);
            hiddenId = view.findViewById(R.id.postId);
            mainLayout = view.findViewById(R.id.mainThreadLayout);
            authorName = view.findViewById(R.id.authorName);
            dateView = view.findViewById(R.id.dateView);
        }
    }
}