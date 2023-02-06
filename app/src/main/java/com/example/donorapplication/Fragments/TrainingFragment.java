package com.example.donorapplication.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.donorapplication.Adapters.ExerciseAdapter;
import com.example.donorapplication.AppDatabase;
import com.example.donorapplication.Entity.Exercise;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.Entity.UserSchedule;
import com.example.donorapplication.R;
import com.example.donorapplication.Utils.AuthUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class TrainingFragment extends Fragment {

    AppDatabase db;
    User currentUser;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public TrainingFragment() {

    }


    public static TrainingFragment newInstance(String param1, String param2) {
        TrainingFragment fragment = new TrainingFragment();
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
        SharedPreferences preferences = getContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
        currentUser = AuthUtil.getUserByToken(preferences, db);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_training, container, false);
        ArrayList<RecyclerView> exerciseRecyclerViews = new ArrayList<RecyclerView>();

        exerciseRecyclerViews.add(view.findViewById(R.id.mondayExercisesRecycler));
        exerciseRecyclerViews.add(view.findViewById(R.id.tuesdayExercisesRecycler));
        exerciseRecyclerViews.add(view.findViewById(R.id.wednesdayExercisesRecycler));
        exerciseRecyclerViews.add(view.findViewById(R.id.thursdayExercisesReycler));
        exerciseRecyclerViews.add(view.findViewById(R.id.fridayExerisesRecycler));
        exerciseRecyclerViews.add(view.findViewById(R.id.saturdayExercisesRecycler));
        exerciseRecyclerViews.add(view.findViewById(R.id.sundayExercisesRecycler));
        for (int i = 0;i<exerciseRecyclerViews.size();i++){
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            exerciseRecyclerViews.get(i).setLayoutManager(layoutManager);
            UserSchedule userSchedule = db.userScheduleDAO().searchForUsersScheduleByDay(i, currentUser.getId());
            String exercisesIdsString = userSchedule.getExercises();
            String[] splited = exercisesIdsString.split("\\s+");
            int[] exerciseIds = new int[splited.length];
            for (int j = 0; j < splited.length; j++) {
                exerciseIds[j] = Integer.parseInt(splited[j]);
            }
            List<Exercise> exercises = db.exerciseDAO().loadAllByIds(exerciseIds);
            ExerciseAdapter exercisesAdapter = new ExerciseAdapter(getContext(), exercises);
            exerciseRecyclerViews.get(i).setAdapter(exercisesAdapter);
        }
        return view;
    }
}