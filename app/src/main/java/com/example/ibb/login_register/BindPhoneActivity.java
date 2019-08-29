package com.example.ibb.login_register;

import android.view.View;
import android.widget.ImageView;

import com.example.ibb.R;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.gyroutils.ParallelViewHelper;

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener {

    private ImageView bind_back_iv;

    @Override
    protected void initview() {

        bind_back_iv =(ImageView)findViewById(R.id.bind_back_iv);

        bind_back_iv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bind_back_iv:
                finish();
                break;
        }
    }
}
