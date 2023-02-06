package com.example.donorapplication;

import static com.example.donorapplication.Home.GALLERY_REQ_CODE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import com.example.donorapplication.Entity.Post;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.Utils.AuthUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CreatingPostActivity extends AppCompatActivity {

    byte[] byteArray;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(this);
        SharedPreferences preferences = this.getSharedPreferences("auth", Context.MODE_PRIVATE);
        User user = AuthUtil.getUserByToken(preferences, db);
        setContentView(R.layout.activity_creating_post);
    }

    public void pick(View view) {
        Intent iGallery = new Intent(Intent.ACTION_PICK);
        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGallery, GALLERY_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED){
            if (resultCode == RESULT_OK){
                if (requestCode == GALLERY_REQ_CODE){
                    Uri imageUri = data.getData();
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byteArray = stream.toByteArray();
                }
            }
        }
    }

    public void createNewPost(View view) {
        SharedPreferences preferences = this.getSharedPreferences("auth", Context.MODE_PRIVATE);
        User user = AuthUtil.getUserByToken(preferences, db);
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String convertedDate = date.toString();
        TextView contentField = (TextView) findViewById(R.id.postContent);
        String content = String.valueOf(contentField.getText());
        Post post = new Post(db.postDao().getAll().size(), content, byteArray, user.getId(), 0, convertedDate);
        db.postDao().createPost(post);
        finish();
    }
}