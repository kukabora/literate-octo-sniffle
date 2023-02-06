package com.example.donorapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "post")
public class Post {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "image")
    private byte[] image;

    @ColumnInfo(name = "authorId")
    private int authorId;

    @ColumnInfo(name = "likesAmount")
    private int likesAmount;

    @ColumnInfo(name = "dateOfCreation")
    private String dateOfCreation;

    public Post(int id, String content, byte[] image, int authorId, int likesAmount, String dateOfCreation) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.authorId = authorId;
        this.likesAmount = likesAmount;
        this.dateOfCreation = dateOfCreation;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getLikesAmount() {
        return likesAmount;
    }

    public void setLikesAmount(int likesAmount) {
        this.likesAmount = likesAmount;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}