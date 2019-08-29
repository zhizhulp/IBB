package com.example.ibb.base;

import android.os.Bundle;

import com.example.ibb.app.MyApp;
import com.umeng.commonsdk.UMConfigure;
import com.zhy.autolayout.AutoLayoutActivity;

public abstract class BaseActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApp.activity = this;
        setContentView(initlayout());
        //UMConfigure.setLogEnabled(true);
        //UMConfigure.setEncryptEnabled(true);
        //MobclickAgent.setScenarioType(MyApp.activity, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //MobclickAgent.setSessionContinueMillis(1000);
        initview();

        initdata();

    }


    protected abstract void initview();

    protected abstract void initdata();

    protected abstract int initlayout();

}
