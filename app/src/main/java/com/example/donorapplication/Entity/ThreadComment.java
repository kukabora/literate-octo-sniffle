package com.example.donorapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "threadComment")
public class ThreadComment {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "authorId")
    private int authorId;

    @ColumnInfo(name="text")
    private String text;

    @ColumnInfo(name="threadId")
    private int threadId;

    @ColumnInfo(name="action")
    private String action;

    @ColumnInfo(name="dateOfCreation")
    private String dateOfCreation;

    public ThreadComment(int id, int authorId, String text, int threadId, String action, String dateOfCreation) {
        this.id = id;
        this.authorId = authorId;
        this.text = text;
        this.threadId = threadId;
        this.action = action;
        this.dateOfCreation = dateOfCreation;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ThreadComment() {
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}