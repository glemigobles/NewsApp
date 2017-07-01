package com.kubaczeremosz.newsapp;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class QueryUtils {

    public static ArrayList<News> extractNews(String requestUrl) throws IOException {

        URL url = createUrl(requestUrl);
        String jasonResponse= makeHttpRequest(url);
        ArrayList<News> newsList = new ArrayList<>();
        if(jasonResponse!=null){
            try {
                JSONObject root = new JSONObject(jasonResponse);
                JSONObject response = root.getJSONObject("response");
                JSONArray items= response.getJSONArray("results");
                for(int i=0; i<items.length();i++){
                    JSONObject currentNews=items.getJSONObject(i);
                    String title =currentNews.getString("title");
                    String date =currentNews.getString("webPublicationDate");
                    String type =currentNews.getString("sectionName");
                    String pageurl =currentNews.getString("webUrl");
                    JSONObject tags = currentNews.getJSONObject("volumeInfo");
                    String author;
                    if(tags.has("webTitle")){
                        author = tags.getString("webTitle");
                    }
                    else{
                        author = "Author N/A";
                    }
                    News news =new News(title,author,pageurl,type,date);
                    newsList.add(news);
                }

            } catch (JSONException e) {
                Log.e("BookAsyncTask", "Problem parsing the books JSON results", e);
            }

            return newsList;
        }
        return null;
    }

    private static URL createUrl(String stringUrl) {
        URL url;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e("URL","Error with creating URL", exception);
            return null;
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(90000 /* milliseconds */);
            urlConnection.setConnectTimeout(12000 /* milliseconds */);
            urlConnection.connect();

            if(urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }

        } catch (IOException e) {
            Log.e("connection","Error with creating connecting", e);
            return null;

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }

        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}
