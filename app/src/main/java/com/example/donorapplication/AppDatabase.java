package com.example.donorapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.donorapplication.DAO.ExerciseDAO;
import com.example.donorapplication.DAO.NewsDAO;
import com.example.donorapplication.DAO.PostDAO;
import com.example.donorapplication.DAO.PostLikeDAO;
import com.example.donorapplication.DAO.ThreadCommentDAO;
import com.example.donorapplication.DAO.ThreadPostDAO;
import com.example.donorapplication.DAO.TopicDAO;
import com.example.donorapplication.DAO.UserDAO;
import com.example.donorapplication.DAO.UserScheduleDAO;
import com.example.donorapplication.Entity.Exercise;
import com.example.donorapplication.Entity.News;
import com.example.donorapplication.Entity.Post;
import com.example.donorapplication.Entity.PostLike;
import com.example.donorapplication.Entity.ThreadComment;
import com.example.donorapplication.Entity.Topic;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.UserSchedule;

@Database(entities = {User.class, Post.class, ThreadPost.class, Exercise.class, Topic.class, ThreadComment.class, PostLike.class, UserSchedule.class, News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "trainingApplication_db";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract UserDAO userDao();
    public abstract PostDAO postDao();
    public abstract ThreadPostDAO threadPostDAO();
    public abstract ThreadCommentDAO threadCommentDAO();
    public abstract ExerciseDAO exerciseDAO();
    public abstract TopicDAO topicDAO();
    public abstract PostLikeDAO postLikeDAO();
    public abstract UserScheduleDAO userScheduleDAO();
    public abstract NewsDAO newsDAO();
}