package com.kubaczeremosz.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.ArrayList;


public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        return null;
    }
}
