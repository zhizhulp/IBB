package com.example.ibb.ui.ui.mine;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.custom_view.ImageView_circle;

import java.util.ArrayList;
import java.util.List;

public class MineActivity extends BaseActivity implements View.OnClickListener {

    List<String> titleList=new ArrayList<>();
    List<Fragment> fragmentList=new ArrayList<>();
    private LinearLayout mine_fans;
    private LinearLayout mine_guanzhu;
    private RelativeLayout mine_attention_relative;
    private RelativeLayout mine_follow_relative;
    private RelativeLayout mine_collection_relative;
    private RelativeLayout mine_setting_relative;
    private RelativeLayout mine_help_relative;
    private ImageView mine_header;

    @Override
    protected void initview() {
        //头像
        mine_header = (ImageView)findViewById(R.id.mine_header);
        mine_header.setOnClickListener(this);

        mine_fans = (LinearLayout)findViewById(R.id.mine_fans);
        mine_fans.setOnClickListener(this);

        mine_guanzhu = (LinearLayout)findViewById(R.id.mine_guanzhu);
        mine_guanzhu.setOnClickListener(this);
        //我关注的问题
        mine_attention_relative = (RelativeLayout)findViewById(R.id.mine_attention_relative);
        mine_attention_relative.setOnClickListener(this);
        //我收藏的回答
        mine_follow_relative = (RelativeLayout)findViewById(R.id.mine_follow_relative);
        mine_follow_relative.setOnClickListener(this);
        //我收藏的商品
        mine_collection_relative = (RelativeLayout)findViewById(R.id.mine_collection_relative);
        mine_collection_relative.setOnClickListener(this);
        //设置
        mine_setting_relative = (RelativeLayout)findViewById(R.id.mine_setting_relative);
        mine_setting_relative.setOnClickListener(this);
        //帮助与反馈
        mine_help_relative = (RelativeLayout)findViewById(R.id.mine_help_relative);
        mine_help_relative.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_mine;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //头像(跳转个人信息)
            case R.id.mine_header:
                startActivity(new Intent(MyApp.activity,PersonalInformationActivity.class));
                break;
            //我的粉丝
            case R.id.mine_fans:
                startActivity(new Intent(MyApp.activity, MineFansActivity.class));
                break;
            //我关注的人
            case R.id.mine_guanzhu:
                startActivity(new Intent(MyApp.activity,MineGuanZhuActivity.class));
                break;
            //我关注的问题
            case R.id.mine_attention_relative:
                startActivity(new Intent(MyApp.activity,MineAttentionActivity.class));
                break;
            //我收藏的回答
            case R.id.mine_follow_relative:
                startActivity(new Intent(MyApp.activity,MineFollowActivity.class));
                break;
            //我收藏的商品
            case R.id.mine_collection_relative:
                startActivity(new Intent(MyApp.activity,MineCollectionActivity.class));
                break;
            //设置
            case R.id.mine_setting_relative:
                startActivity(new Intent(MyApp.activity,MineSettingActivity.class));
                break;
            //帮助与反馈
            case R.id.mine_help_relative:
                startActivity(new Intent(MyApp.activity,MineHelpActivity.class));
                break;
        }

    }
}
