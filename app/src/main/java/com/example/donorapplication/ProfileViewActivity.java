package com.example.donorapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.donorapplication.Adapters.ProfilePostAdapter;
import com.example.donorapplication.Entity.Post;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.Utils.AuthUtil;
import com.example.donorapplication.Utils.DateHelper;
import com.example.donorapplication.Utils.ImageToByteArray;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProfileViewActivity extends AppCompatActivity {


    AppDatabase db;
    List<Post> posts;
    RecyclerView postRecycler;
    ImageView avatarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        db = AppDatabase.getInstance(this);
        Bundle b = getIntent().getExtras();
        int userId = -1;
        if(b != null)
            userId = b.getInt("userId");
        User user = db.userDao().getById(userId);
        posts = db.postDao().getPostsByOwnerId(user.getId());
        TextView profileNameView = (TextView) findViewById(R.id.userNameView);
        postRecycler = (RecyclerView) findViewById(R.id.profilePostsRecycler2);
        postRecycler.setLayoutManager(new LinearLayoutManager(this));
        ProfilePostAdapter adapter = new ProfilePostAdapter(this, posts);
        postRecycler.setAdapter(adapter);
        TextView profileAgeView = (TextView) findViewById(R.id.profileAgeField);
        TextView threadsAmountView = (TextView) findViewById(R.id.threadsAmountView);
        avatarView = findViewById(R.id.avatarImage);
        int amountOfThreads = db.threadPostDAO().getThreadsByAuthorId(user.getId()).size();
        threadsAmountView.setText(String.valueOf(amountOfThreads) + " threads");
        profileNameView.setText("Name: " + String.valueOf(user.getFirstName()) + " " + String.valueOf(user.getLastName()));
        profileAgeView.setText("Age: " + String.valueOf(user.getAge()));
        TextView streakField = findViewById(R.id.profileStreakField);
        TextView commentsField =  findViewById(R.id.answeredThreadsAmount);
        String difference = String.valueOf(DateHelper.getDateDiff(new Date(user.getAccountCreationDate()), new Date(), TimeUnit.DAYS) + 1);
        streakField.setText(String.valueOf(difference) + " days streak");
        commentsField.setText(String.valueOf(db.threadCommentDAO().getCommentsByAuthorId(user.getId()).size() + " comments"));
        if (user.getAvatar() == null) {
            avatarView.setBackgroundResource(R.drawable.profile_placeholder_avatar);
        }
        else {
            Bitmap bm = null;
            try {
                bm = ImageToByteArray.bytesToImage(user.getAvatar());
            } catch (Exception e) {
                e.printStackTrace();
            }
            avatarView.setImageBitmap(bm);
        }
    }
}