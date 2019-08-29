package com.example.ibb.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ibb.MainActivity;
import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.login_register.StartRegisterActivity;

public class SplashActivity extends BaseActivity {


    @Override
    protected void initview() {

    }

    @Override
    protected void initdata() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MyApp.activity,MainActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_start;
    }

}
