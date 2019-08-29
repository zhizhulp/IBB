package com.example.ibb.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 张凯雅 on 2018/1/2.
 */

public class OkHttpUtils  {

    private static OkHttpUtils okHttpUtils;
    private final OkHttpClient okHttpClient;

    public OkHttpUtils() {
        okHttpClient = new OkHttpClient();
    }

    public static OkHttpUtils getInstance(){
        if (okHttpUtils == null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils == null){
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    public void get(String url,Callback callback) {
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);

    }

    public void post(String url, RequestBody requestBody ,Callback callback) {
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
