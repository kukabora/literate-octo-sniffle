package com.example.donorapplication.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.Topic;

import java.util.List;

@Dao
public interface TopicDAO {

    @Query("SELECT * FROM topic")
    List<Topic> getAll();

    @Query("SELECT * FROM topic WHERE id = :id")
    Topic getById(int id);

    @Query("SELECT name FROM topic")
    String[] getTopicNames();

    @Query("SELECT * from topic where name = :topicName")
    Topic getTopicByName(String topicName);

    @Insert
    void insert(Topic topic);
}
