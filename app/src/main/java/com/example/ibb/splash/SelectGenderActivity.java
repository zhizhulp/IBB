package com.example.ibb.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ibb.MainActivity;
import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.lianxi.MainActivity_viewpager;

public class SelectGenderActivity extends BaseActivity implements View.OnClickListener {

    private Button selectgenter_bt;
    private TextView selectgenter_jump_tv;

    @Override
    protected void initview() {
        //跳过
        selectgenter_jump_tv = (TextView)findViewById(R.id.selectgenter_jump_tv);
        selectgenter_jump_tv.setOnClickListener(this);

        selectgenter_bt = (Button)findViewById(R.id.selectgenter_bt);
        selectgenter_bt.setOnClickListener(this);


    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_select_gender;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.selectgenter_jump_tv:
                startActivity(new Intent(MyApp.activity,MainActivity.class));
                break;

            case R.id.selectgenter_bt:
                startActivity(new Intent(MyApp.activity,MainActivity.class));
                break;
        }
    }
}
