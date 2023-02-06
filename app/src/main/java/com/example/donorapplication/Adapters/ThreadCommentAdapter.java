package com.example.donorapplication.Adapters;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donorapplication.AppDatabase;
import com.example.donorapplication.Entity.ThreadComment;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.ProfileViewActivity;
import com.example.donorapplication.R;
import com.example.donorapplication.ThreadPostActivity;
import com.example.donorapplication.Utils.AuthUtil;
import com.example.donorapplication.Utils.ImageToByteArray;

import java.util.List;

public class ThreadCommentAdapter extends RecyclerView.Adapter<ThreadCommentAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<ThreadComment> comments;
    AppDatabase db;


    public ThreadCommentAdapter(Context context, List<ThreadComment> comments) {
        this.comments = comments;
        db = AppDatabase.getInstance(context);
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ThreadCommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.thread_post_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ThreadCommentAdapter.ViewHolder holder, int position) {
        ThreadComment comment = comments.get(position);
        User user = db.userDao().getById(comment.getAuthorId());
        SharedPreferences preferences = inflater.getContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
        User currentUser = AuthUtil.getUserByToken(preferences, db);
        holder.authorSaid.setText(String.valueOf(user.getFirstName()) + " " + String.valueOf(user.getLastName()) + " said:");
        holder.dateOfCommenting.setText(String.valueOf(comment.getDateOfCreation()));
        holder.mainComment.setText(String.valueOf(comment.getText()));
        holder.actionField.setText(String.valueOf(comment.getAction()));

        if (comment.getAuthorId() == currentUser.getId()){
            holder.authorSaid.setTextColor(Color.parseColor("#1CA553"));
        } else {
            holder.authorSaid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context , ProfileViewActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("userId", user.getId());
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
        }

        if (String.valueOf(comment.getAction()).equals("UPVOTED")){
            holder.actionField.setTextColor(Color.parseColor("#1CA553"));
        }
        else {
            holder.actionField.setTextColor(Color.parseColor("#E83636"));
        }

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView authorSaid, dateOfCommenting, mainComment, actionField;

        ViewHolder(View view){
            super(view);
            authorSaid = view.findViewById(R.id.authorSaid);
            dateOfCommenting =  view.findViewById(R.id.dateOfCCommenting);
            mainComment = view.findViewById(R.id.mainCommentContent);
            actionField = view.findViewById(R.id.actionField);
        }
    }
}