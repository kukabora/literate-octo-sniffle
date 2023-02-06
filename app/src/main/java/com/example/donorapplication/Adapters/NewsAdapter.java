package com.example.donorapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donorapplication.Entity.Exercise;
import com.example.donorapplication.Entity.News;
import com.example.donorapplication.Entity.PostLike;
import com.example.donorapplication.R;
import com.example.donorapplication.ShowNewsEntityActivity;
import com.example.donorapplication.ThreadPostActivity;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<News> news;

    public NewsAdapter(Context context, List<News> news) {
        this.news = news;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.new_preview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        News newsEntity = news.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(newsEntity.getImage(), 0, newsEntity.getImage().length);
        holder.newsImage.setImageBitmap(bitmap);
        holder.newsTitle.setText(String.valueOf(newsEntity.getTitle()));
        holder.domainCreator.setText(String.valueOf(newsEntity.getSourceDomain()));
        holder.dateOfCreation.setText(String.valueOf(newsEntity.getCreationDate()));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context , ShowNewsEntityActivity.class);
                Bundle b = new Bundle();
                b.putInt("newsId", newsEntity.getId());
                intent.putExtras(b);
                context.startActivity(intent);
                }
            }
        );
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView newsTitle, domainCreator, dateOfCreation;
        final ImageView newsImage;
        final ConstraintLayout mainLayout;

        ViewHolder(View view){
            super(view);
            newsTitle = view.findViewById(R.id.newsTitle);
            domainCreator =  view.findViewById(R.id.domainAuthor);
            dateOfCreation = view.findViewById(R.id.newsCreationDate);
            newsImage = view.findViewById(R.id.newImage);
            mainLayout = view.findViewById(R.id.newsElementMainLayout);
        }
    }
}