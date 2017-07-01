package com.kubaczeremosz.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>> {

    private static String QUERRY_REQUEST="http://content.guardianapis.com/search?q=debate&section=technology&tag=technology/technology&show-tags=contributor&order-by=newest&page-size=20&api-key=test";
    private static final int NEWS_LOADER_ID = 1;

    private NewsListAdapter mAdapter;
    private TextView mText;
    private View loadingIndicator;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //message view
        mText= (TextView) findViewById(R.id.messageText);
        //loading indicator
        loadingIndicator=findViewById(R.id.loading_indicator);
        //listView
        listView= (ListView) findViewById(R.id.list_view);
        mAdapter= new NewsListAdapter(this, new ArrayList<News>());

        //check connection
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            mText.setText(R.string.msg_wait);
            loadingIndicator.setVisibility(View.VISIBLE);
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, MainActivity.this);

        }
        else{
            mText.setText(R.string.msg_no_connection);
            loadingIndicator.setVisibility(View.GONE);
        }
    }


    @Override
    public Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, QUERRY_REQUEST);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> data) {
        loadingIndicator.setVisibility(View.GONE);
        mText.setVisibility(View.GONE);
        if (mAdapter != null) {
            mAdapter.clear();
        }
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
        if (data != null && data.isEmpty()) {
            mText.setText(R.string.nothingfound);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {
            mAdapter.clear();
    }
}
