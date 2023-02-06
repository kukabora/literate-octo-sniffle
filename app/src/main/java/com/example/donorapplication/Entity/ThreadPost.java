package com.example.donorapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "threadPost")
public class ThreadPost {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "thesis")
    private String thesis;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "authorResource")
    private int authorResource;

    @ColumnInfo(name = "answersAmount")
    private int answersAmount;

    @ColumnInfo(name = "bannedAmount")
    private int bannedAmount;

    @ColumnInfo(name = "authorId")
    private int authorId;

    @ColumnInfo(name = "categoryId")
    private int categoryId;

    @ColumnInfo(name = "dateOfCreation")
    private String dateOfCreation;

    public ThreadPost(int id, String thesis, String description, int authorResource, int answersAmount, int bannedAmount, int authorId, int categoryId, String  dateOfCreation) {
        this.id = id;
        this.thesis = thesis;
        this.description = description;
        this.authorResource = authorResource;
        this.answersAmount = answersAmount;
        this.bannedAmount = bannedAmount;
        this.authorId = authorId;
        this.categoryId = categoryId;
        this.dateOfCreation = dateOfCreation;
    }

    public ThreadPost() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThesis() {
        return thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorResource() {
        return authorResource;
    }

    public void setAuthorResource(int authorResource) {
        this.authorResource = authorResource;
    }

    public int getAnswersAmount() {
        return answersAmount;
    }

    public void setAnswersAmount(int answersAmount) {
        this.answersAmount = answersAmount;
    }

    public int getBannedAmount() {
        return bannedAmount;
    }

    public void setBannedAmount(int bannedAmount) {
        this.bannedAmount = bannedAmount;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}