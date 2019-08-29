package com.example.ibb.entity;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 张凯雅 on 2018/3/8.
 */

public class SearchResultsBean {
    private String title;
    private ImageView imageView;
    private Boolean check = false;
    private TextView price;

    public SearchResultsBean() {
    }

    public SearchResultsBean(String title, ImageView imageView, Boolean check, TextView price) {
        this.title = title;
        this.imageView = imageView;
        this.check = check;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }
}
