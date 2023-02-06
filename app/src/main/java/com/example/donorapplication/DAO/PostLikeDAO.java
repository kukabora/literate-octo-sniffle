package com.example.donorapplication.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.donorapplication.Entity.PostLike;
import com.example.donorapplication.Entity.ThreadPost;

import java.util.List;

@Dao
public interface PostLikeDAO {

    @Query("SELECT * FROM postLike")
    List<PostLike> getAll();

    @Query("SELECT * FROM postLike WHERE postId = :postId")
    List<PostLike> getLikesByPostId(int postId);

    @Query("SELECT COUNT(1) from postLike where postId = :postId and authorId = :userId")
    Boolean checkIfUserLikedPost(int postId, int userId);

    @Insert
    void insert(PostLike postLike);
}
