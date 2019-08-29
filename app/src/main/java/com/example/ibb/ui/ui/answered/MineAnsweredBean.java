package com.example.ibb.ui.ui.answered;

import java.io.Serializable;

/**
 * Created by 张凯雅 on 2018/3/20.
 */

public class MineAnsweredBean implements Serializable{

    private int LINE_TYPE = 3;

    public int getLINE_TYPE() {
        return LINE_TYPE;
    }

    public void setLINE_TYPE(int LINE_TYPE) {
        this.LINE_TYPE = LINE_TYPE;
    }

    private String title;

    public MineAnsweredBean() {
    }

    public MineAnsweredBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
