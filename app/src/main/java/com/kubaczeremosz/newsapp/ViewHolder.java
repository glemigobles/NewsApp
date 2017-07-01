package com.kubaczeremosz.newsapp;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Kuba on 2017-07-01.
 */

public class ViewHolder {

    public TextView titleTextView;
    public TextView authorTextView;
    public TextView dateTextView;
    public TextView sectionTextView;
    public Button readButton;

    public ViewHolder(@NonNull View view) {
        this.titleTextView = (TextView)view
                .findViewById(R.id.news_title);
        this.authorTextView = (TextView)view
                .findViewById(R.id.news_author);
        this.dateTextView = (TextView)view
                .findViewById(R.id.news_date);
        this.sectionTextView = (TextView)view
                .findViewById(R.id.news_section);
        this.readButton=(Button)view
                .findViewById(R.id.news_read);
    }
}

