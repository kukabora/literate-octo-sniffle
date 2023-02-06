package com.example.donorapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class User {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "fName")
    public String firstName;

    @ColumnInfo(name = "lName")
    public String lastName;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "weight")
    public int weight;

    @ColumnInfo(name = "height")
    public int height;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "bodyFat")
    public int bodyFat;

    @ColumnInfo(name = "freeTimePerWeek")
    public int freeTimePerWeek;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name="avatar")
    private byte[] avatar;

    @ColumnInfo(name = "estimatedWeight")
    public int estimatedWeight;

    @ColumnInfo(name = "interestingTopics")
    public String interestingTopics;

    @ColumnInfo(name = "accountCreationDate")
    public String accountCreationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(int bodyFat) {
        this.bodyFat = bodyFat;
    }

    public int getFreeTimePerWeek() {
        return freeTimePerWeek;
    }

    public void setFreeTimePerWeek(int freeTimePerWeek) {
        this.freeTimePerWeek = freeTimePerWeek;
    }

    public int getEstimatedWeight() {
        return estimatedWeight;
    }

    public void setEstimatedWeight(int estimatedWeight) {
        this.estimatedWeight = estimatedWeight;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getInterestingTopics() {
        return interestingTopics;
    }

    public void setInterestingTopics(String interestingTopics) {
        this.interestingTopics = interestingTopics;
    }

    public User(int id, String firstName, String lastName, String email, int weight, int height, int age, String password, int bodyFat, int freeTimePerWeek, int estimatedWeight, byte[] avatar, String interestingTopics, String accountCreationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.password = password;
        this.bodyFat = bodyFat;
        this.freeTimePerWeek = freeTimePerWeek;
        this.estimatedWeight = estimatedWeight;
        this.avatar = avatar;
        this.interestingTopics = interestingTopics;
        this.accountCreationDate = accountCreationDate;
    }

    public String getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(String accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }
}