package com.example.ibb.ui.ui.mine;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ibb.R;
import com.example.ibb.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class AnswerdActivity extends BaseActivity implements View.OnClickListener {


    private ImageView answerd_back_iv;
    private TabLayout answerd_tabLayout;
    private ViewPager answerd_viewPager;
    private List<String> titleList=new ArrayList<>();
    private List<Fragment> fragmentList=new ArrayList<>();

    @Override
    protected void initview() {
        answerd_back_iv = (ImageView)findViewById(R.id.answerd_back_iv);
        answerd_tabLayout = (TabLayout)findViewById(R.id.answerd_tabLayout);
        answerd_viewPager = (ViewPager)findViewById(R.id.answerd_viewPager);

        titleList.add("我的提问");
        titleList.add("我的回答");

        answerd_tabLayout.addTab(answerd_tabLayout.newTab().setText(titleList.get(0)));
        answerd_tabLayout.addTab(answerd_tabLayout.newTab().setText(titleList.get(1)));

        fragmentList.add(new MineQuestionFragment());
        fragmentList.add(new MineAnsweredFragment());

        MineAnswerdPagerAdapter mineAnswerdPagerAdapter = new MineAnswerdPagerAdapter(getSupportFragmentManager());
        answerd_viewPager.setAdapter(mineAnswerdPagerAdapter);

        //用来绑定viewpager和TabLayout
        answerd_tabLayout.setupWithViewPager(answerd_viewPager);

        answerd_viewPager.setCurrentItem(1);
        answerd_tabLayout.getTabAt(1).select();

        answerd_back_iv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_answerd;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.answerd_back_iv:
                finish();
                break;
        }
    }

    private class MineAnswerdPagerAdapter extends FragmentPagerAdapter {
        public MineAnswerdPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
