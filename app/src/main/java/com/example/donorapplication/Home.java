package com.example.donorapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donorapplication.Adapters.ThreadPostAdapter;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.Topic;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.Fragments.HomeFragment;
import com.example.donorapplication.Fragments.ProfileFragment;
import com.example.donorapplication.Fragments.SearchFragment;
import com.example.donorapplication.Fragments.SocialFragment;
import com.example.donorapplication.Fragments.TrainingFragment;
import com.example.donorapplication.Utils.AuthUtil;
import com.example.donorapplication.Utils.CustomHashing;
import com.example.donorapplication.Utils.SharedPreferencesToList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    AppDatabase db;
    BottomNavigationView bottomNavigationView;
    static final int GALLERY_REQ_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = AppDatabase.getInstance(this);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    HomeFragment homeFragment = new HomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    SearchFragment searchFragment = new SearchFragment();
    SocialFragment socialFragment = new SocialFragment();
    TrainingFragment trainingFragment = new TrainingFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                return true;

            case R.id.social:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, socialFragment).commit();
                return true;

            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, searchFragment).commit();
                return true;

            case R.id.training:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, trainingFragment).commit();
                return true;

            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
                return true;
        }
        return false;
    }

    public void pick(View view) {
        Intent iGallery = new Intent(Intent.ACTION_PICK);
        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGallery, GALLERY_REQ_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED){
            if (resultCode == RESULT_OK){
                if (requestCode == GALLERY_REQ_CODE){
                    Uri imageUri = data.getData();
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    SharedPreferences preferences = Home.this.getSharedPreferences("auth", Context.MODE_PRIVATE);
                    User user = AuthUtil.getUserByToken(preferences, db);
                    user.setAvatar(byteArray);
                    db.userDao().update(user);
                    profileFragment.setImageViewAvatar(data.getData());
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    public void pickTopic(View view){
        ConstraintLayout mainLayout = (ConstraintLayout) view.findViewById(R.id.mainLayout);
        ImageView ifImageViewExists = mainLayout.findViewById(2281337);
        TextView hiddenIdField = (TextView) view.findViewById(R.id.hiddenTopicId);
        String topicId = String.valueOf(hiddenIdField.getText());
        SharedPreferences preferences = getSharedPreferences("topics", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (ifImageViewExists == null){
            ImageView imageView = new ImageView(this);
            imageView.setId(2281337);
            imageView.setImageResource(R.drawable.selected_topic);
            mainLayout.addView(imageView);
            editor.putString("topics", preferences.getString("topics", "") + topicId + " ");
            editor.apply();
            System.out.println(preferences.getString("topics", ""));
        }
        else {
            mainLayout.removeView(ifImageViewExists);
            String newSharedPrefString = preferences.getString("topics", "").replace(topicId + " ", "");
            editor.putString("topics", newSharedPrefString);
            editor.apply();
            System.out.println(preferences.getString("topics", ""));

        }
    }


    public void submitTopics(View view){
        SharedPreferences preferences = getSharedPreferences("topics", MODE_PRIVATE);
        if (preferences.getString("topics", "").equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Choose your topics first!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            String topicsString = preferences.getString("topics", "");
            topicsString = topicsString.substring(0, topicsString.length() - 1);
            SharedPreferences authPreferences = getSharedPreferences("auth", MODE_PRIVATE);
            User user = AuthUtil.getUserByToken(authPreferences, db);
            user.setInterestingTopics(topicsString);
            db.userDao().update(user);
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, socialFragment).commit();
        }
    }

    public void startCreatePostActivity(View view){
        Context context = view.getContext();
        Intent intent = new Intent(context , CreatingPostActivity.class);
        context.startActivity(intent);
    }

    public void startChangingScheduleActivity(View view){
        Context context = view.getContext();
        Intent intent = new Intent(context , ChangingScheduleActivity.class);
        context.startActivity(intent);
    }

    public void startCreatingThreadPostActivity(View view){
        Context context = view.getContext();
        Intent intent = new Intent(context , CreatingThreadPostActivity.class);
        context.startActivity(intent);
    }
}