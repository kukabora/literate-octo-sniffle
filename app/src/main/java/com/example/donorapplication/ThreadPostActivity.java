package com.example.donorapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.donorapplication.Adapters.ProfilePostAdapter;
import com.example.donorapplication.Adapters.ThreadCommentAdapter;
import com.example.donorapplication.Entity.ThreadComment;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.Utils.AuthUtil;
import com.example.donorapplication.Utils.ImageToByteArray;

import java.util.List;

public class ThreadPostActivity extends AppCompatActivity {

    AppDatabase db;
    List<ThreadComment> comments;
    ThreadPost currentThreadPost;

    protected void setThreadPost(ThreadPost threadPost){
        currentThreadPost = threadPost;
        SharedPreferences preferences = this.getSharedPreferences("auth", Context.MODE_PRIVATE);
        User currentUser = AuthUtil.getUserByToken(preferences, db);
        ImageView avatarField = findViewById(R.id.ownerAvatar);
        TextView authorField = findViewById(R.id.ownerName);
        TextView postDate = findViewById(R.id.postDate);
        TextView thesisField = findViewById(R.id.postThesis);
        TextView upvotesAmount = findViewById(R.id.upvotesAmount);
        TextView downvotesAmount = findViewById(R.id.downvotesAmount);
        TextView descriptionField = findViewById(R.id.descriptionView);
        Button commentBtn = findViewById(R.id.commentBtn);

        if (db.threadPostDAO().checkIfThreadOwner(threadPost.getId(), currentUser.getId()) || db.threadCommentDAO().checkIfUserCommentedOnThread(threadPost.getId(), currentUser.getId())){
            commentBtn.setVisibility(View.GONE);
        }

        User owner = db.userDao().getById(threadPost.getAuthorId());
        byte[] userAvatar = owner.getAvatar();
        if (userAvatar == null){
            avatarField.setBackgroundResource(R.drawable.profile_placeholder_avatar);
        } else {
            Bitmap bm = null;
            try {
                bm = ImageToByteArray.bytesToImage(userAvatar);
            } catch (Exception e) {
                e.printStackTrace();
            }
            avatarField.setImageBitmap(bm);
        }

        authorField.setText(String.valueOf(owner.getFirstName()) + " " + String.valueOf(owner.getLastName()));
        postDate.setText(String.valueOf(threadPost.getDateOfCreation()));
        thesisField.setText(String.valueOf(threadPost.getThesis()));
        upvotesAmount.setText(String.valueOf(threadPost.getAnswersAmount()));
        downvotesAmount.setText(String.valueOf(threadPost.getBannedAmount()));
        descriptionField.setText(String.valueOf(threadPost.getDescription()));

        comments = db.threadCommentDAO().getCommentsByThreadId(threadPost.getId());

        RecyclerView commentRecycler = findViewById(R.id.commentsRecycler);
        commentRecycler.setLayoutManager(new LinearLayoutManager(this));
        ThreadCommentAdapter adapter = new ThreadCommentAdapter(this, comments);
        commentRecycler.setAdapter(adapter);
        avatarField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context , ProfileViewActivity.class);
                Bundle b = new Bundle();
                b.putInt("userId", owner.getId());
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });
        authorField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context , ProfileViewActivity.class);
                Bundle b = new Bundle();
                b.putInt("userId", owner.getId());
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(this);

        setContentView(R.layout.activity_thread_post);
        Bundle b = getIntent().getExtras();
        int threadPostId = -1;
        if(b != null)
            threadPostId = b.getInt("threadPostId");
        ThreadPost threadPost = db.threadPostDAO().getById(threadPostId);
        setThreadPost(threadPost);
    }

    public void startCreateCommentActivity(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context , CommentingActivity.class);
        Bundle b = new Bundle();
        b.putInt("threadPostId", currentThreadPost.getId());
        intent.putExtras(b);
        context.startActivity(intent);
    }
}