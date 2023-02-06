package com.example.donorapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userschedule")
public class UserSchedule {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "daynumber")
    private int daynumber;

    @ColumnInfo(name = "exercises")
    private String exercises;

    @ColumnInfo(name = "ownnerId")
    private int ownerId;

    public UserSchedule() {
    }

    public UserSchedule(int id, int daynumber, String exercises, int ownerId) {
        this.id = id;
        this.daynumber = daynumber;
        this.exercises = exercises;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDaynumber() {
        return daynumber;
    }

    public void setDaynumber(int daynumber) {
        this.daynumber = daynumber;
    }

    public String getExercises() {
        return exercises;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}