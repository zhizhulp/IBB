package com.example.ibb.okhttp;

/**
 * Created by ASUS on 2018/5/11.
 */

public interface HttpListener {
    <T> void handle200True(int what,T data);
    <T> void handle200False(int what,T data);
    <T> void handleFailed(int what,String error);
    void handleException(int what,Exception e);
}
