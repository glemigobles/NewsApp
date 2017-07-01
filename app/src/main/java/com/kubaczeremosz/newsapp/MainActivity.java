package com.kubaczeremosz.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static String QUERRY_REQUEST="http://content.guardianapis.com/search?q=debate&section=technology&tag=technology/technology&show-tags=contributor&order-by=newest&page-size=20&api-key=test";
    private static final int BOOK_LOADER_ID = 1;
    private static String GOOGLE_BOOKS_REQUEST_URL;
    private NewsListAdapter mAdapter;

    private TextView mText;
    private View loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
