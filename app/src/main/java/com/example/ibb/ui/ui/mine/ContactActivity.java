package com.example.ibb.ui.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ibb.R;
import com.example.ibb.base.BaseActivity;

public class ContactActivity extends BaseActivity implements View.OnClickListener {

    private ImageView contact_back_iv;

    @Override
    protected void initview() {
        //返回
        contact_back_iv = (ImageView)findViewById(R.id.contact_back_iv);
        contact_back_iv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_contact;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回
            case R.id.contact_back_iv:
                finish();
                break;
        }
    }
}
