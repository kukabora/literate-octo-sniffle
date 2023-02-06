package com.example.donorapplication.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.donorapplication.Adapters.ExerciseAdapter;
import com.example.donorapplication.Adapters.NewsAdapter;
import com.example.donorapplication.Adapters.ThreadPostAdapter;
import com.example.donorapplication.AppDatabase;
import com.example.donorapplication.Entity.Exercise;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.R;
import com.example.donorapplication.Utils.AuthUtil;
import com.example.donorapplication.Utils.ImageToByteArray;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class HomeFragment extends Fragment {

    AppDatabase db;

    private RecyclerView threadsRecyclerView;
    private RecyclerView exercisesRecyclerView;
    private RecyclerView newsRecyclerView;
    List<ThreadPost> threadPosts;
    List<Exercise> exercises;
    private TextView dateText;
    private TextView greetingText;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        db = AppDatabase.getInstance(this.getActivity());
        threadPosts = db.threadPostDAO().getNewestThreads();
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("auth", Context.MODE_PRIVATE);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        greetingText = (TextView) view.findViewById(R.id.greetingText);
        dateText = (TextView) view.findViewById(R.id.dateText);

        int userId = AuthUtil.getUserByToken(preferences, db).getId();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int dayNumber = cal.get(Calendar.DAY_OF_WEEK);
        dayNumber += 5;
        dayNumber %= 7;
        System.out.println("TODAYS DAY OF WEEK IS " + dayNumber + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        String exercisesIdsString = db.userScheduleDAO().searchForUsersScheduleByDay(dayNumber, userId).getExercises();
        String[] splited = exercisesIdsString.split("\\s+");
        int[] exerciseIds = new int[splited.length];
        for (int j = 0; j < splited.length; j++) {
            exerciseIds[j] = Integer.parseInt(splited[j]);
        }

        exercises = db.exerciseDAO().loadAllByIds(exerciseIds);

        String name = AuthUtil.getUserByToken(preferences, db).getFirstName();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        greetingText.setText("Welcome, " + name);
        dateText.setText(date.toString());
        threadsRecyclerView = view.findViewById(R.id.trendingThreadslist);
        threadsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ThreadPostAdapter adapter = new ThreadPostAdapter(getContext(), threadPosts);
        threadsRecyclerView.setAdapter(adapter);
        exercisesRecyclerView = view.findViewById(R.id.exercisesRecyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        exercisesRecyclerView.setLayoutManager(layoutManager);
        ExerciseAdapter exercisesAdapter = new ExerciseAdapter(getContext(), exercises);
        exercisesRecyclerView.setAdapter(exercisesAdapter);

        newsRecyclerView = view.findViewById(R.id.newsRecyclerView);
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        newsRecyclerView.setLayoutManager(layoutManager2);
        NewsAdapter newsAdapter = new NewsAdapter(getContext(), db.newsDAO().getAll());
        newsRecyclerView.setAdapter(newsAdapter);
        return view;
    }

}