package com.example.donorapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.donorapplication.Adapters.ChangingExerciseAdapter;
import com.example.donorapplication.Adapters.TopicAdapter;
import com.example.donorapplication.Entity.Exercise;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.Entity.UserSchedule;
import com.example.donorapplication.Utils.AuthUtil;

import java.util.List;

public class ChangingScheduleActivity extends AppCompatActivity {

    RecyclerView changingScheduleRecycler;
    Spinner spinner;
    AppDatabase db;
    List<Exercise> exercises;
    int dayNumber;

    String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(getApplicationContext());
        exercises = db.exerciseDAO().getAll();
        setContentView(R.layout.activity_changing_schedule);
        changingScheduleRecycler = findViewById(R.id.changingScheduleRecycler);
        spinner = findViewById(R.id.dayOfWeekSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, daysOfWeek);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dayNumber = i;
                GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                changingScheduleRecycler.setLayoutManager(layoutManager);
                changingScheduleRecycler.setItemAnimator(new DefaultItemAnimator());
                ChangingExerciseAdapter adapter = new ChangingExerciseAdapter(getApplicationContext(), exercises);
                changingScheduleRecycler.setAdapter(adapter);
                SharedPreferences preferences = getSharedPreferences("choosenExercise", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("choosenExercise", "");
                editor.apply();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("NOTHING SELECTED");
            }
        });
    }

    public void saveChoosenExercises(View view) {
        SharedPreferences preferences = getSharedPreferences("choosenExercise", Context.MODE_PRIVATE);
        String choosenExercisesId = preferences.getString("choosenExercise", "");
        if (choosenExercisesId.equals("")) {
            Toast toast = Toast.makeText(this,
                    "You have to choose at least one exercise!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            SharedPreferences authPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE);
            User currentUser = AuthUtil.getUserByToken(authPreferences, db);
            UserSchedule userSchedule = db.userScheduleDAO().searchForUsersScheduleByDay(dayNumber, currentUser.getId());
            userSchedule.setExercises(choosenExercisesId);
            db.userScheduleDAO().update(userSchedule);
            Toast toast = Toast.makeText(this,
                    "Changes applied!", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
    }
}