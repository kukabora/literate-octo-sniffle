package com.example.donorapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "postLike")
public class PostLike {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "authorId")
    private int authorId;

    @ColumnInfo(name="postId")
    private int postId;


    public PostLike(int id, int authorId, int postId) {
        this.id = id;
        this.authorId = authorId;
        this.postId = postId;
    }

    public PostLike() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
