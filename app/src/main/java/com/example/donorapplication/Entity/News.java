package com.example.donorapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "news")
public class News {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "creationDate")
    private String creationDate;

    @ColumnInfo(name = "sourceDomain")
    private String sourceDomain;

    @ColumnInfo(name = "detailedDescription")
    private String detailedDescription;

    @ColumnInfo(name = "postUrl")
    private String posturl;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getSourceDomain() {
        return sourceDomain;
    }

    public void setSourceDomain(String sourceDomain) {
        this.sourceDomain = sourceDomain;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getPosturl() {
        return posturl;
    }

    public void setPosturl(String posturl) {
        this.posturl = posturl;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public News() {
    }

    public News(int id, String title, String creationDate, String sourceDomain, String detailedDescription, String posturl, byte[] image) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.sourceDomain = sourceDomain;
        this.detailedDescription = detailedDescription;
        this.posturl = posturl;
        this.image = image;
    }
}