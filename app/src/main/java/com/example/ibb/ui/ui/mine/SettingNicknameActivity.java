package com.example.ibb.ui.ui.mine;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.entity.User;
import com.example.ibb.utils.AppConfig;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.ibb.R.id.nickname_edit;

public class SettingNicknameActivity extends BaseActivity implements View.OnClickListener {


    private ImageView nickname_back_iv;
    private EditText set_nickname_edit;

    @Override
    protected void initview() {
        //返回键
        nickname_back_iv = (ImageView) findViewById(R.id.nickname_back_iv);
        set_nickname_edit = (EditText) findViewById(R.id.set_nickname_edit);
        nickname_back_iv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_setting_nickname;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nickname_back_iv:
                setNickName();
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void setNickName() {
        SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
        final String token = user.getString("token", null);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("token", token)
                        .build();
                return chain.proceed(request);
            }
        }).build();
        String nickname = set_nickname_edit.getText().toString();
        FormBody body1 = new FormBody.Builder()
                .add("nickname", nickname)
                .build();
        Request request1 = new Request.Builder().url(URLApi.setNickname).post(body1).build();
        client.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
        AppConfig.getInstance().putString("nickname",nickname);
    }

    @Override
    public void onBackPressed() {
        setNickName();
        finish();
    }
}
