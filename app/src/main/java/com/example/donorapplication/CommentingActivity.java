package com.example.donorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.donorapplication.Entity.ThreadComment;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.Utils.AuthUtil;

public class CommentingActivity extends AppCompatActivity {

    AppDatabase db;
    ThreadPost currentThreadPost;
    TextView commentField;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commenting);
        db = AppDatabase.getInstance(this);
        SharedPreferences preferences = this.getSharedPreferences("auth", Context.MODE_PRIVATE);
        currentUser = AuthUtil.getUserByToken(preferences, db);
        Bundle b = getIntent().getExtras();
        int threadPostId = -1;
        if(b != null)
            threadPostId = b.getInt("threadPostId");
        currentThreadPost = db.threadPostDAO().getById(threadPostId);
        commentField = findViewById(R.id.commentSectionEditText);
    }

    public void commentWithUpvote(View view) {
        String comment = String.valueOf(commentField.getText());
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String dateOfCreation = date.toString();
        db.threadCommentDAO().insert(new ThreadComment(db.threadCommentDAO().getAll().size(), currentUser.getId(), comment, currentThreadPost.getId(), "UPVOTED", dateOfCreation));
        currentThreadPost.setAnswersAmount(currentThreadPost.getAnswersAmount() + 1);
        db.threadPostDAO().update(currentThreadPost);
        finish();
    }

    public void commentWithDownvote(View view) {
        String comment = String.valueOf(commentField.getText());
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String dateOfCreation = date.toString();
        db.threadCommentDAO().insert(new ThreadComment(db.threadCommentDAO().getAll().size(), currentUser.getId(), comment, currentThreadPost.getId(), "DOWNVOTED", dateOfCreation));
        currentThreadPost.setBannedAmount(currentThreadPost.getBannedAmount() + 1);
        db.threadPostDAO().update(currentThreadPost);
        finish();
    }
}