package com.kubaczeremosz.newsapp;


public class News {

    private String title;
    private String author;
    private String url;
    private String section;
    private String date;

    public News(String title, String author, String url, String section, String date) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.section = section;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getSection() {
        return section;
    }

    public String getDate() {
        return date;
    }
}
