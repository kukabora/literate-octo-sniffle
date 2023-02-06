package com.example.donorapplication.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.donorapplication.Entity.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE fName LIKE :first AND " +
            "lName LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Query("SELECT * FROM user WHERE email LIKE :login")
    User findByLogin(String login);

    @Query("SELECT * FROM user WHERE email LIKE :email AND " +
            "password LIKE :password LIMIT 1")
    User authenticate(String email, String password);

    @Query("SELECT * FROM user WHERE id = :id")
    User getById(int id);

    @Query("SELECT * FROM user WHERE fName LIKE :searchInput or lName LIKE :searchInput")
    List<User> searchForUsers(String searchInput);

    @Update
    void update(User user);

    @Insert
    void register(User user);

    @Delete
    void delete(User user);
}
