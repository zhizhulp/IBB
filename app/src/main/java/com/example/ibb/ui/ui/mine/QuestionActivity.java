package com.example.ibb.ui.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ibb.R;
import com.example.ibb.base.BaseActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout question_tabLayout;
    private ViewPager question_viewPager;
    private List<String> titleList=new ArrayList<>();
    private List<Fragment> fragmentList=new ArrayList<>();
    private ImageView question_back_iv;

    public static void startMine(Context context,int position){
        Intent intent=new Intent(context,QuestionActivity.class);
        intent.putExtra("position",position);
        context.startActivity(intent);
    }

    @Override
    protected void initview() {
        int position = getIntent().getIntExtra("position", 0);
        question_back_iv = (ImageView)findViewById(R.id.question_back_iv);
        question_tabLayout = (TabLayout)findViewById(R.id.question_tabLayout);
        question_viewPager = (ViewPager)findViewById(R.id.question_viewPager);

        titleList.add("我的提问");
        titleList.add("我的回答");

        question_tabLayout.addTab(question_tabLayout.newTab().setText(titleList.get(0)));
        question_tabLayout.addTab(question_tabLayout.newTab().setText(titleList.get(1)));

        fragmentList.add(new MineQuestionFragment());
        fragmentList.add(new MineAnsweredFragment());
        MinePagerAdapter minePagerAdapter = new MinePagerAdapter(getSupportFragmentManager());
        question_viewPager.setAdapter(minePagerAdapter);

        //用来绑定viewpager和TabLayout
        question_tabLayout.setupWithViewPager(question_viewPager);
        question_back_iv.setOnClickListener(this);

        question_tabLayout.getTabAt(position).select();
        minePagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_question;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.question_back_iv:
                finish();
                break;
        }
    }

    private class MinePagerAdapter extends FragmentPagerAdapter {
        public MinePagerAdapter(FragmentManager fm) {
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
