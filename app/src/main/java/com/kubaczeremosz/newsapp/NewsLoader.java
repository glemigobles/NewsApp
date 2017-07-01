package com.kubaczeremosz.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;


public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        this.mUrl=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of news.
        ArrayList<News> newsList = null;
        try {
            newsList = QueryUtils.extractNews(mUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
