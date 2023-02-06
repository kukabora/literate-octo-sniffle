package com.example.donorapplication.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.donorapplication.Adapters.ExerciseAdapter;
import com.example.donorapplication.Adapters.ThreadPostAdapter;
import com.example.donorapplication.Adapters.TopicAdapter;
import com.example.donorapplication.AppDatabase;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.Topic;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.R;
import com.example.donorapplication.Utils.AuthUtil;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class SocialFragment extends Fragment {

    RecyclerView recyclerView;
    List<Topic> topics;
    AppDatabase db;
    List<ThreadPost> threadPosts;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public SocialFragment() {

    }

    public static SocialFragment newInstance(String param1, String param2) {
        SocialFragment fragment = new SocialFragment();
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
        topics = db.topicDAO().getAll();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("auth", Context.MODE_PRIVATE);
        User user = AuthUtil.getUserByToken(preferences, db);
        if (user.getInterestingTopics() != null){
            View view = inflater.inflate(R.layout.topic_page, container, false);
            String str = user.getInterestingTopics();
            String[] splited = str.split("\\s+");
            Integer[] topicIds = Stream.of(splited).map(Integer::valueOf).toArray(Integer[]::new);
            for (int i = 0;i<topicIds.length;i++){
                Topic topic = db.topicDAO().getById(topicIds[i]);
                String topicName = topic.getName();
                threadPosts = db.threadPostDAO().getNewestThreadsByCategoryId(topic.getId());
                RecyclerView recyclerView = new RecyclerView(getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                recyclerView.setNestedScrollingEnabled(false);
                ThreadPostAdapter adapter = new ThreadPostAdapter(getContext(), threadPosts);
                recyclerView.setAdapter(adapter);
                LinearLayout linearLayout = view.findViewById(R.id.threadsLayout);
                TextView textView = new TextView(getContext());
                textView.setText(String.valueOf(topicName) + ":");
                textView.setTextColor(Color.parseColor("#FFFFFF"));
                textView.setPadding(35, 35, 35, 35);
                textView.setTextSize(35);
                linearLayout.addView(textView);
                linearLayout.addView(recyclerView);
            }
            return view;
        }
        else {
            View view = inflater.inflate(R.layout.fragment_social, container, false);
            recyclerView = view.findViewById(R.id.topicsRecyclerView);
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            TopicAdapter adapter = new TopicAdapter(getContext(), topics);
            recyclerView.setAdapter(adapter);
            return view;
        }
    }

}