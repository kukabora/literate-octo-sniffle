package com.example.donorapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "topic")
public class Topic {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name="image")
    private int image;

    public Topic(int id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public Topic() {
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}