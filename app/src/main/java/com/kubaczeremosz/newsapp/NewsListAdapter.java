package com.kubaczeremosz.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;


public class NewsListAdapter extends ArrayAdapter<News> {

    private ArrayList<News> newsList;


    public NewsListAdapter(@NonNull Context context, ArrayList<News> newsList) {
        super(context, 0, newsList);
        this.newsList = newsList;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //viewholder instance

        ViewHolder viewHolder;

        // Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        final News currentNews = newsList.get(position);

        viewHolder.titleTextView.setText(currentNews.getTitle());
        viewHolder.authorTextView.setText(currentNews.getAuthor());
        viewHolder.dateTextView.setText(currentNews.getDate());
        viewHolder.sectionTextView.setText(currentNews.getSection());
        viewHolder.readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri newsUri = Uri.parse(currentNews.getUrl());
                Intent i = new Intent(Intent.ACTION_VIEW, newsUri);
                getContext().startActivity(i);
            }
        });

        return convertView;
    }


    public class ViewHolder {

        public TextView titleTextView;
        public TextView authorTextView;
        public TextView dateTextView;
        public TextView sectionTextView;
        public Button readButton;

        public ViewHolder(@NonNull View view) {
            this.titleTextView = (TextView) view
                    .findViewById(R.id.news_title);
            this.authorTextView = (TextView) view
                    .findViewById(R.id.news_author);
            this.dateTextView = (TextView) view
                    .findViewById(R.id.news_date);
            this.sectionTextView = (TextView) view
                    .findViewById(R.id.news_section);
            this.readButton = (Button) view
                    .findViewById(R.id.news_read);
        }
    }
}


