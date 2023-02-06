package com.example.donorapplication.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.donorapplication.Entity.User;
import com.example.donorapplication.Entity.UserSchedule;

import java.util.List;

@Dao
public interface UserScheduleDAO {

    @Query("SELECT * FROM userschedule")
    List<UserSchedule> getAll();

    @Query("SELECT * FROM userschedule WHERE id = :id")
    UserSchedule getById(int id);

    @Query("SELECT * FROM userschedule where daynumber = :daynumber and ownnerId = :ownerId")
    UserSchedule searchForUsersScheduleByDay(int daynumber, int ownerId);

    @Query("SELECT * from userschedule where ownnerId = :ownerId")
    List<UserSchedule> getAllUserschedules(int ownerId);

    @Update
    void update(UserSchedule userSchedule);

    @Insert
    void insert(UserSchedule userSchedule);

}
