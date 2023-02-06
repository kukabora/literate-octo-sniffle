package com.example.donorapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donorapplication.Adapters.ChangingExerciseAdapter;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.Topic;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.Utils.AuthUtil;

public class CreatingThreadPostActivity extends AppCompatActivity {

    EditText titleField, descriptionField;
    AppDatabase db;
    Spinner topicSpinner;
    Topic choosenTopic;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = AppDatabase.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_thread_post);
        SharedPreferences preferences = getSharedPreferences("auth", Context.MODE_PRIVATE);
        user = AuthUtil.getUserByToken(preferences, db);
        titleField = findViewById(R.id.titleOfThreadField);
        descriptionField = findViewById(R.id.threadDescriptionField);
        topicSpinner = findViewById(R.id.threadPostTopicSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, db.topicDAO().getTopicNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        topicSpinner.setAdapter(adapter);
        topicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choosenTopic = db.topicDAO().getById(i);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("NOTHING IS SELECTED");
            }
        });
    }

    public void saveThreadPost(View view) {
        String title = titleField.getText().toString();
        String desc = descriptionField.getText().toString();
        if (title == null ||desc == null){
            Toast toast = Toast.makeText(this,
                    "Fields can not be empty", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String dateOfCreation = date.toString();

        db.threadPostDAO().insert(new ThreadPost(db.threadPostDAO().getAll().size(), title, desc, 0, 0, 0, user.getId(), choosenTopic.getId(), dateOfCreation));
        Toast toast = Toast.makeText(this,
                "Thread has been successfully created!", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
}