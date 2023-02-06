package com.example.donorapplication.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.donorapplication.Entity.Exercise;
import com.example.donorapplication.Entity.Topic;
import com.example.donorapplication.R;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Topic> topics;

    public TopicAdapter(Context context, List<Topic> topics) {
        this.topics = topics;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public TopicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.topic_preview_icon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicAdapter.ViewHolder holder, int position) {
        Topic topic = topics.get(position);
        holder.topicImage.setImageResource(topic.getImage());
        holder.topicName.setText(String.valueOf(topic.getName()));
        holder.hiddenTopicId.setText(String.valueOf(topic.getId()));
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView topicName, hiddenTopicId;
        final ImageView topicImage;

        ViewHolder(View view){
            super(view);
            topicName = view.findViewById(R.id.topicName);
            topicImage = view.findViewById(R.id.topicImage);
            hiddenTopicId = view.findViewById(R.id.hiddenTopicId);
        }
    }
}