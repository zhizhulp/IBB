package com.example.ibb.entity;

import com.example.ibb.ui.ui.answered.Bean;

/**
 * Created by 张凯雅 on 2018/1/30.
 */

public class Been {
    private String title;
    private String nr;
    private String bq;
    private String twobq;
    private String zan;
    private String ping;
    private String imageview;
    private Imageviews image;

    public Been() {
    }

    public Been(String title, String nr, String bq, String twobq, String zan, String ping, String imageview, Imageviews image) {
        this.title = title;
        this.nr = nr;
        this.bq = bq;
        this.twobq = twobq;
        this.zan = zan;
        this.ping = ping;
        this.imageview = imageview;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getBq() {
        return bq;
    }

    public void setBq(String bq) {
        this.bq = bq;
    }

    public String getTwobq() {
        return twobq;
    }

    public void setTwobq(String twobq) {
        this.twobq = twobq;
    }

    public String getZan() {
        return zan;
    }

    public void setZan(String zan) {
        this.zan = zan;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public String getImageview() {
        return imageview;
    }

    public void setImageview(String imageview) {
        this.imageview = imageview;
    }

    public Imageviews getImage() {
        return image;
    }

    public void setImage(Imageviews image) {
        this.image = image;
    }
}
