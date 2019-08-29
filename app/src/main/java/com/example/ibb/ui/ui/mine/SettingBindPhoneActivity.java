package com.example.ibb.ui.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ibb.R;
import com.example.ibb.base.BaseActivity;

public class SettingBindPhoneActivity extends BaseActivity implements View.OnClickListener {

    private ImageView bindphone_back_iv;

    @Override
    protected void initview() {

        bindphone_back_iv = (ImageView)findViewById(R.id.bindphone_back_iv);
        bindphone_back_iv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_setting_bind_phone;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bindphone_back_iv:
                finish();
                break;
        }
    }
}
