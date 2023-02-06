package com.example.donorapplication.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.donorapplication.Entity.ThreadComment;
import com.example.donorapplication.Entity.ThreadPost;

import java.util.List;

@Dao
public interface ThreadCommentDAO {

    @Query("SELECT * FROM threadComment")
    List<ThreadComment> getAll();

    @Query("SELECT * FROM threadComment WHERE threadId = :threadId")
    List<ThreadComment> getCommentsByThreadId(int threadId);

    @Query("SELECT * FROM threadComment WHERE authorId = :authorId")
    List<ThreadComment> getCommentsByAuthorId(int authorId);

    @Query("SELECT * FROM threadComment WHERE threadId = :threadId ORDER BY id DESC LIMIT 15")
    List<ThreadComment> getNewestCommentsOnThread(int threadId);

    @Query("SELECT COUNT(1) from threadComment where threadId = :threadId and authorId = :userId")
    Boolean checkIfUserCommentedOnThread(int threadId, int userId);

    @Insert
    void insert(ThreadComment threadComment);
}
