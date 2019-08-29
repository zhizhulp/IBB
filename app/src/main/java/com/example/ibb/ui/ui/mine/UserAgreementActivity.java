package com.example.ibb.ui.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.ibb.R;
import com.example.ibb.base.BaseActivity;

public class UserAgreementActivity extends BaseActivity implements View.OnClickListener {

    private ImageView userAgreement_back_iv;
    private WebView userAgreement_webview;

    @Override
    protected void initview() {

        //返回键
        userAgreement_back_iv = (ImageView)findViewById(R.id.userAgreement_back_iv);
        userAgreement_back_iv.setOnClickListener(this);
        //网页
        userAgreement_webview = (WebView)findViewById(R.id.userAgreement_webview);
    }

    @Override
    protected void initdata() {
        //加载webview网页(用户协议)
        userAgreement_webview.loadUrl("http://www.ibbtech.cn/protocol/");
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_user_agreement;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.userAgreement_back_iv :
                finish();
                break;

        }
    }
}
