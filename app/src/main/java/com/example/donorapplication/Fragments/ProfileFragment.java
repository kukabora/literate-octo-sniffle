package com.example.donorapplication.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.donorapplication.Adapters.ProfilePostAdapter;
import com.example.donorapplication.Adapters.ThreadPostAdapter;
import com.example.donorapplication.AppDatabase;
import com.example.donorapplication.Entity.Post;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.R;
import com.example.donorapplication.Utils.AuthUtil;
import com.example.donorapplication.Utils.DateHelper;
import com.example.donorapplication.Utils.ImageToByteArray;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProfileFragment extends Fragment {


    AppDatabase db;
    RecyclerView postRecycler;
    List<Post> posts;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ImageView avatarView;


    private String mParam1;
    private String mParam2;

    public ProfileFragment() {

    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(this.getActivity());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences preferences = this.getActivity().getSharedPreferences("auth", Context.MODE_PRIVATE);
        User user = AuthUtil.getUserByToken(preferences, db);
        TextView streakField = view.findViewById(R.id.profileStreakField);
        TextView commentsField =  view.findViewById(R.id.answeredThreadsAmount);
        String difference = String.valueOf(DateHelper.getDateDiff(new Date(user.getAccountCreationDate()), new Date(), TimeUnit.DAYS) + 1);
        streakField.setText(String.valueOf(difference) + " days streak");
        commentsField.setText(String.valueOf(db.threadCommentDAO().getCommentsByAuthorId(user.getId()).size() + " comments"));
        posts = db.postDao().getPostsByOwnerId(user.getId());
        TextView profileNameView = (TextView) view.findViewById(R.id.userNameView);
        postRecycler = view.findViewById(R.id.profilePostsRecycler);
        postRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ProfilePostAdapter adapter = new ProfilePostAdapter(getContext(), posts);
        postRecycler.setAdapter(adapter);
        postRecycler.setBackgroundColor(Color.parseColor("#2C2A2C"));
        TextView profileAgeView = (TextView) view.findViewById(R.id.profileAgeField);
        TextView threadsAmountView = (TextView) view.findViewById(R.id.threadsAmountView);
        avatarView = view.findViewById(R.id.avatarImage);
        int amountOfThreads = db.threadPostDAO().getThreadsByAuthorId(user.getId()).size();
        threadsAmountView.setText(String.valueOf(amountOfThreads) + " threads");
        profileNameView.setText("Name: " + String.valueOf(user.getFirstName()) + " " + String.valueOf(user.getLastName()));
        profileAgeView.setText("Age: " + String.valueOf(user.getAge()));
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
        return view;
    }
    public void setImageViewAvatar(Uri url){
        avatarView.setImageURI(url);
    }
}