package com.example.donorapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donorapplication.AppDatabase;
import com.example.donorapplication.Entity.Exercise;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.ProfileViewActivity;
import com.example.donorapplication.R;
import com.example.donorapplication.ThreadPostActivity;
import com.example.donorapplication.Utils.AuthUtil;
import com.example.donorapplication.Utils.ImageToByteArray;

import java.util.List;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.ViewHolder>{


    AppDatabase db;
    private final LayoutInflater inflater;
    private final List<User> users;

    public UserSearchAdapter(Context context, List<User> users) {
        this.users = users;
        db = AppDatabase.getInstance(context);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public UserSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.user_search_preview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserSearchAdapter.ViewHolder holder, int position) {
        SharedPreferences preferences = inflater.getContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
        User currentUser = AuthUtil.getUserByToken(preferences, db);
        User user = users.get(position);
        holder.username.setText(String.valueOf(user.getFirstName()) + " " + String.valueOf(user.getLastName()));
        if (user.getAvatar() != null){
            Bitmap bm = null;
            try {
                bm = ImageToByteArray.bytesToImage(user.getAvatar());
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.userAvatar.setImageBitmap(bm);
        }
        else{
            holder.userAvatar.setImageResource(R.drawable.profile_placeholder_avatar);
        }
        if (user.getId() != currentUser.getId()){
            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView username;
        final ImageView userAvatar;
        final ConstraintLayout mainLayout;

        ViewHolder(View view){
            super(view);
            username = view.findViewById(R.id.userSearchName);
            userAvatar =  view.findViewById(R.id.userSearchAvatar);
            mainLayout =  view.findViewById(R.id.mainSearchUserLayout);
        }
    }
}