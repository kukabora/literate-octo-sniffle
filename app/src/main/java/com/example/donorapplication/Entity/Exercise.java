package com.example.donorapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise")
public class Exercise {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "sets")
    private int sets;

    @ColumnInfo(name = "estimatedTime")
    private int estimatedTime;

    @ColumnInfo(name = "authorId")
    private int authorId;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public Exercise(int id, String name, int sets, int estimatedTime, int authorId, byte[] image) {
        this.id = id;
        this.name = name;
        this.sets = sets;
        this.estimatedTime = estimatedTime;
        this.authorId = authorId;
        this.image = image;
    }

    public Exercise() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}