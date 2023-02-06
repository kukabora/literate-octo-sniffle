package com.example.donorapplication.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.donorapplication.Entity.Exercise;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.R;

import java.util.List;

public class ExerciseAdapter  extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Exercise> exercises;

    public ExerciseAdapter(Context context, List<Exercise> exercises) {
        this.exercises = exercises;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.exercise_preview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExerciseAdapter.ViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(exercise.getImage(), 0, exercise.getImage().length);
        holder.exerciseImage.setImageBitmap(bitmap);
        holder.exerciseAmount.setText(String.valueOf(exercise.getSets()));
        holder.exercisseEstimatedTime.setText(String.valueOf(exercise.getEstimatedTime()));
        holder.exerciseName.setText(String.valueOf(exercise.getName()));
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView exerciseName, exerciseAmount, exercisseEstimatedTime;
        final ImageView exerciseImage;

        ViewHolder(View view){
            super(view);
            exerciseName = view.findViewById(R.id.exerciseName);
            exerciseAmount =  view.findViewById(R.id.exerciseAmount);
            exercisseEstimatedTime = view.findViewById(R.id.exerciseEstimatedTime);
            exerciseImage = view.findViewById(R.id.exerciseImage);
        }
    }
}