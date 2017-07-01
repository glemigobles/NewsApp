package com.kubaczeremosz.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Kuba on 2017-07-01.
 */

public class NewsListAdapter extends ArrayAdapter<News> {


    public NewsListAdapter(@NonNull Context context, ArrayList<News> newsList) {
        super(context, 0, newsList);
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //viewholder instance

        ViewHolder viewHolder;

        // Check if the existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        News currentNews = getItem(position);

        viewHolder.titleTextView.setText(currentNews.getTitle());
        viewHolder.authorTextView.setText(currentNews.getAuthor());
        viewHolder.dateTextView.setText(currentNews.getDate());
        viewHolder.sectionTextView.setText(currentNews.getSection());

        //ViewGroup item =(ViewGroup) convertView.findViewById(R.id.item);
        return convertView;
    }
}

