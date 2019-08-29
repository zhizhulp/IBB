package com.example.ibb.ui.ui.find;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.adapter.BigImageViewAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class BigImageActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager bigImage_vp;
    private TextView picture_display_text;
    private List<Fragment> fragmentList;
    private int pos;
    private ImageView bigImage_back_iv;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            picture_display_text.setText((pos + 1) + "/" +fragmentList.size());
        }
    };


    @Override
    protected void initview() {

        bigImage_vp = (ViewPager)findViewById(R.id.bigImage_vp);
        picture_display_text = (TextView)findViewById(R.id.picture_display_text);

        bigImage_back_iv = (ImageView)findViewById(R.id.bigImage_back_iv);
        bigImage_back_iv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

        bigImage_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                pos = position;
                handler.sendEmptyMessage(100);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragmentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            fragmentList.add(new BigImageFragment());
        }

        BigImageViewAdapter viewPagerAdapter = new BigImageViewAdapter(getSupportFragmentManager(), MyApp.activity,fragmentList);
        bigImage_vp.setAdapter(viewPagerAdapter);
        bigImage_vp.setOffscreenPageLimit(fragmentList.size());
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_big_image;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bigImage_back_iv:
                finish();
                break;

        }
    }
}
