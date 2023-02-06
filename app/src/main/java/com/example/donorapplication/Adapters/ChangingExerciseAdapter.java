package com.example.donorapplication.Adapters;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donorapplication.Entity.Exercise;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.R;
import com.example.donorapplication.Utils.AuthUtil;

import java.util.List;

public class ChangingExerciseAdapter extends RecyclerView.Adapter<ChangingExerciseAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Exercise> exercises;

    public ChangingExerciseAdapter(Context context, List<Exercise> exercises) {
        this.exercises = exercises;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ChangingExerciseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.exercise_preview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChangingExerciseAdapter.ViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(exercise.getImage(), 0, exercise.getImage().length);
        holder.exerciseImage.setImageBitmap(bitmap);
        holder.exerciseAmount.setText(String.valueOf(exercise.getSets()));
        holder.baseCellImage.setImageResource(R.drawable.training_element);
        holder.exerciseHiddenId.setText(String.valueOf(exercise.getId()));
        holder.exercisseEstimatedTime.setText(String.valueOf(exercise.getEstimatedTime()));
        holder.exerciseName.setText(String.valueOf(exercise.getName()));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                @SuppressLint("ResourceType") ImageView ifImageViewExists = holder.mainLayout.findViewById(14881337);
                String exerciseId = String.valueOf(holder.exerciseHiddenId.getText());
                SharedPreferences preferences = inflater.getContext().getSharedPreferences("choosenExercise", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                if (ifImageViewExists == null){
                    ImageView imageView = new ImageView(inflater.getContext());
                    imageView.setId(14881337);
                    imageView.setImageResource(R.drawable.selected_topic);
                    holder.mainLayout.addView(imageView);
                    imageView.getLayoutParams().height = 350;
                    imageView.getLayoutParams().width = 350;

                    editor.putString("choosenExercise", preferences.getString("choosenExercise", "") + exerciseId + " ");
                    editor.apply();
                    System.out.println(preferences.getString("choosenExercise", ""));
                }
                else {
                    holder.mainLayout.removeView(ifImageViewExists);
                    String newSharedPrefString = preferences.getString("choosenExercise", "").replace(exerciseId + " ", "");
                    editor.putString("choosenExercise", newSharedPrefString);
                    editor.apply();
                    System.out.println(preferences.getString("choosenExercise", ""));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView exerciseName, exerciseAmount, exercisseEstimatedTime, exerciseHiddenId;
        final ImageView exerciseImage, baseCellImage;
        final ConstraintLayout mainLayout;

        ViewHolder(View view){
            super(view);
            exerciseName = view.findViewById(R.id.exerciseName);
            exerciseAmount =  view.findViewById(R.id.exerciseAmount);
            exercisseEstimatedTime = view.findViewById(R.id.exerciseEstimatedTime);
            exerciseImage = view.findViewById(R.id.exerciseImage);
            baseCellImage = view.findViewById(R.id.baseCellImage);
            exerciseHiddenId = view.findViewById(R.id.exerciseHiddenId);
            mainLayout = view.findViewById(R.id.mainExerciseElementLayout);
        }
    }
}