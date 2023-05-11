package com.example.wallpapersapp;

public class categoryRvModel {

    private String category;
    private String categoryIvURL;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryIvURL() {
        return categoryIvURL;
    }

    public void setCategoryIvURL(String categoryIvURL) {
        this.categoryIvURL = categoryIvURL;
    }

    public categoryRvModel(String category, String categoryIvURL) {
        this.category = category;
        this.categoryIvURL = categoryIvURL;
    }
}
