package com.example.ibb.ui.ui.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;

public class MineHelpActivity extends BaseActivity implements View.OnClickListener {

    private Button help_fback_button;
    private ImageView help_back_iv;
    private RelativeLayout help_user_relative;
    private RelativeLayout lssuequestion_rl;
    private RelativeLayout viewfollow_rl;
    private RelativeLayout topanswer_rl;
    private RelativeLayout upgrade_rl;
    private RelativeLayout found_rl;
    private RelativeLayout contact_rl;

    @Override
    protected void initview() {
        //返回
        help_back_iv = (ImageView)findViewById(R.id.help_back_iv);
        help_back_iv.setOnClickListener(this);
        //爱哔哔是什么?
        help_user_relative = (RelativeLayout)findViewById(R.id.help_user_relative);
        help_user_relative.setOnClickListener(this);
        //如何发布问题?
        lssuequestion_rl = (RelativeLayout)findViewById(R.id.lssuequestion_rl);
        lssuequestion_rl.setOnClickListener(this);
        //哪里查看我关注的问题/收藏的回答/收藏的商品？
        viewfollow_rl = (RelativeLayout)findViewById(R.id.viewfollow_rl);
        viewfollow_rl.setOnClickListener(this);
        //热门答主在哪里查看？
        topanswer_rl = (RelativeLayout)findViewById(R.id.topanswer_rl);
        topanswer_rl.setOnClickListener(this);
        //为什么升级/重装后我的收藏不见了？
        upgrade_rl = (RelativeLayout)findViewById(R.id.upgrade_rl);
        upgrade_rl.setOnClickListener(this);
        //如果发现违规内容怎么办？
        found_rl = (RelativeLayout)findViewById(R.id.found_rl);
        found_rl.setOnClickListener(this);
        //如何联系我们？
        contact_rl = (RelativeLayout)findViewById(R.id.contact_rl);
        contact_rl.setOnClickListener(this);
        //我要反馈按钮
        help_fback_button = (Button)findViewById(R.id.help_fback_button);
        help_fback_button.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_mine_help;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.help_back_iv:
                finish();
                break;
            //爱哔哔是什么?
            case R.id.help_user_relative:
                startActivity(new Intent(MyApp.activity,HelpUserActivity.class));
                break;
            //如何发布问题?
            case R.id.lssuequestion_rl:
                startActivity(new Intent(MyApp.activity,LssueQuestionActivity.class));
                break;
            //哪里查看我关注的问题/收藏的回答/收藏的商品？
            case R.id.viewfollow_rl:
                startActivity(new Intent(MyApp.activity,ViewFollowActivity.class));
                break;
            //热门答主在哪里查看？
            case R.id.topanswer_rl:
                startActivity(new Intent(MyApp.activity,TopAnswerActivity.class));
                break;
            //为什么升级/重装后我的收藏不见了？
            case R.id.upgrade_rl:
                startActivity(new Intent(MyApp.activity,UpGradeActivity.class));
                break;
            //如果发现违规内容怎么办？
            case R.id.found_rl:
                startActivity(new Intent(MyApp.activity,FoundActivity.class));
                break;
            //如何联系我们？
            case R.id.contact_rl:
                startActivity(new Intent(MyApp.activity,ContactActivity.class));
                break;
            //我要反馈按钮
            case R.id.help_fback_button:
                startActivity(new Intent(MyApp.activity,FeedBackActivity.class));
                break;
            //
        }
    }
}
