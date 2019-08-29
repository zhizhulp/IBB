package com.example.ibb.entity;

/**
 * Created by 吕楠 on 2018/4/3.
 */

public class MyEventBusBean {

    private String message;

    public MyEventBusBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
