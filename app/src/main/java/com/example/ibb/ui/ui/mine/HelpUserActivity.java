package com.example.ibb.ui.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ibb.R;
import com.example.ibb.base.BaseActivity;

public class HelpUserActivity extends BaseActivity implements View.OnClickListener {

    private ImageView helpuser_back_iv;

    @Override
    protected void initview() {

        helpuser_back_iv = (ImageView)findViewById(R.id.helpuser_back_iv);
        helpuser_back_iv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_help_user;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.helpuser_back_iv:
                finish();
                break;

        }
    }
}
