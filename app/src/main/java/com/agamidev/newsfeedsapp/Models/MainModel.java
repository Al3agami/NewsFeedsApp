package com.agamidev.newsfeedsapp.Models;

import java.util.ArrayList;

public class MainModel {
    private String status;
    private String source;
    private String sortBy;
    private ArrayList<NewsModel> articles;

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public ArrayList<NewsModel> getArticles() {
        return articles;
    }
}
