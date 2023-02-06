package com.example.donorapplication.Utils;

import android.content.SharedPreferences;

import com.example.donorapplication.AppDatabase;
import com.example.donorapplication.Entity.User;

public class AuthUtil {

    public static boolean checkToken(SharedPreferences preferences){
        String token = preferences.getString("token",null);
        if (token == null || token.equals("")){
            return false;
        }
        return true;
    }

    public static User getUserByToken(SharedPreferences preferences, AppDatabase db){
        String token = preferences.getString("token",null);
        int spaceIndex = token.indexOf(" ")+1;
        int lineIndex = token.indexOf("|");
        int userId = Integer.valueOf(token.substring(spaceIndex, lineIndex));
        User user = db.userDao().getById(userId);
        return user;
    }

}
