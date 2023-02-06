package com.example.donorapplication.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.donorapplication.Entity.Exercise;
import com.example.donorapplication.Entity.Post;

import java.util.List;

@Dao
public interface ExerciseDAO {

    @Query("SELECT * FROM exercise")
    List<Exercise> getAll();

    @Query("SELECT * FROM exercise WHERE id IN (:exerciseIds)")
    List<Exercise> loadAllByIds(int[] exerciseIds);

    @Insert
    void insert(Exercise exercise);

    @Delete
    void delete(Exercise exercise);
}
