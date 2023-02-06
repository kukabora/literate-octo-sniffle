package com.example.donorapplication.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.donorapplication.Entity.Exercise;
import com.example.donorapplication.Entity.News;

import java.util.List;

@Dao
public interface NewsDAO {

    @Query("SELECT * FROM news")
    List<News> getAll();

    @Query("SELECT * from news where id = :newsId")
    News getById(int newsId);

    @Query("SELECT * FROM news LIMIT 5")
    List<News> getFreshNews();

    @Insert
    void insert(News news);

    @Delete
    void delete(News news);
}
