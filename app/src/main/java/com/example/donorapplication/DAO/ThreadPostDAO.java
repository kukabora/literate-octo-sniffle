package com.example.donorapplication.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.donorapplication.Entity.Post;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.Topic;

import java.util.List;

@Dao
public interface ThreadPostDAO {

    @Query("SELECT * FROM threadPost")
    List<ThreadPost> getAll();

    @Query("SELECT * FROM threadPost WHERE id IN (:threadIds)")
    List<ThreadPost> loadAllByIds(int[] threadIds);

    @Query("SELECT * FROM threadPost WHERE id = :threadPostId")
    ThreadPost getById(int threadPostId);

    @Query("SELECT * FROM threadPost WHERE categoryId = :categoryId")
    List<ThreadPost> getAllByCategoryId(int categoryId);

    @Query("SELECT * FROM threadPost WHERE authorId = :authorId")
    List<ThreadPost> getThreadsByAuthorId(int authorId);

    @Query("SELECT * FROM threadPost ORDER BY id DESC LIMIT 5")
    List<ThreadPost> getNewestThreads();

    @Query("SELECT * FROM threadPost WHERE categoryId = :categoryId ORDER BY id DESC LIMIT 5")
    List<ThreadPost> getNewestThreadsByCategoryId(int categoryId);

    @Query("SELECT * FROM threadPost WHERE description LIKE :searchInput  or thesis LIKE :searchInput")
    List<ThreadPost> searchForThreadPosts(String searchInput);

    @Query("SELECT COUNT(1) from threadPost where id = :threadId and authorId = :userId")
    Boolean checkIfThreadOwner(int threadId, int userId);

    @Update
    void update(ThreadPost threadPost);

    @Insert
    void insert(ThreadPost threadPost);
}
