package com.example.donorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.donorapplication.Entity.News;
import com.example.donorapplication.Entity.ThreadPost;

public class ShowNewsEntityActivity extends AppCompatActivity {

    AppDatabase db;
    News newsEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news_entity);
        db = AppDatabase.getInstance(this);
        Bundle b = getIntent().getExtras();
        int newsId = -1;
        if(b != null)
            newsId = b.getInt("newsId");
        newsEntity = db.newsDAO().getById(newsId);
        TextView newsTitle = findViewById(R.id.newsActivityTitle);
        TextView newsContent = findViewById(R.id.newsActivityContent);
        TextView newsDateOfCreation = findViewById(R.id.newsActivityDateOfCreation);
        TextView newsAuthorDomain = findViewById(R.id.newsActivityDomainAuthor);
        newsTitle.setText(String.valueOf(newsEntity.getTitle()));
        newsContent.setText(String.valueOf(newsEntity.getDetailedDescription()));
        newsDateOfCreation.setText(String.valueOf(newsEntity.getCreationDate()));
        newsAuthorDomain.setText(String.valueOf(newsEntity.getSourceDomain()));
        ImageView newsImage = findViewById(R.id.mainNewsPageBackground);
        Bitmap bitmap = BitmapFactory.decodeByteArray(newsEntity.getImage(), 0, newsEntity.getImage().length);
        newsImage.setImageBitmap(bitmap);
    }

    public void openNewsInBrowser(View view) {
        Uri uri = Uri.parse(newsEntity.getPosturl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}