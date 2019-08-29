package com.example.ibb.ui.ui.answered.model;

import android.os.Handler;

import com.example.ibb.ui.ui.answered.Bean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 张凯雅 on 2018/1/30.
 */

public class OkHttpmodel implements OkHttpView{
    Handler handler=new Handler();
    @Override
    public void Banners(String url, final OkHttplistener okHttplistener) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                       Bean bean= gson.fromJson(string, Bean.class);
                        okHttplistener.OnSuccess(bean);
                    }
                });
            }
        });
    }
}
