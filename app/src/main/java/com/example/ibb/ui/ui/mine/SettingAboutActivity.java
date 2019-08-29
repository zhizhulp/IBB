package com.example.ibb.ui.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ibb.R;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.utils.ShareUtils;

public class SettingAboutActivity extends BaseNetActivity implements View.OnClickListener {

    private ImageView about_back_iv;

    protected void initview() {
        about_back_iv = (ImageView) findViewById(R.id.about_back_iv);
        about_back_iv.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_about);
        initview();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.about_back_iv:
                finish();
                break;

        }
    }

    public void share(View view) {
        ShareUtils.share(this,false,"http://homepage.ibbtech.cn/","爱哔哔","商品咋样，一问便知",null);

    }
}
