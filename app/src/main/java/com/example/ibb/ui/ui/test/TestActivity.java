package com.example.ibb.ui.ui.test;

import android.os.Bundle;
import android.view.View;

import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.crash.CrashManager;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.yanzhenjie.nohttp.rest.Request;

public class TestActivity extends BaseNetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        CrashManager.getInstance();
        Request request = buildRequest(URLApi.uploadImage, HttpBaseEntity.class);
        doRequest(0,request);
    }

    @Override
    protected void handleFailed(int what, String error) {
        super.handleFailed(what, error);
        findViewById(R.id.btn_apply).setVisibility(View.VISIBLE);
    }
}
