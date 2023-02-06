package com.example.donorapplication.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.donorapplication.Entity.Post;
import com.example.donorapplication.Entity.User;

import java.util.List;

@Dao
public interface PostDAO {

    @Query("SELECT * FROM post")
    List<Post> getAll();

    @Query("SELECT * FROM post WHERE id IN (:postIds)")
    List<Post> loadAllByIds(int[] postIds);

    @Query("SELECT * FROM post WHERE authorId = :authorId")
    List<Post> getPostsByOwnerId(int authorId);

    @Query("SELECT * FROM post WHERE content LIKE :searchInput")
    List<Post> searchForPosts(String searchInput);

    @Update
    void update(Post post);

    @Insert
    void createPost(Post post);

    @Delete
    void delete(Post post);
}
