package com.example.ibb.lianxi;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ibb.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_viewpager extends AppCompatActivity {

    private TabLayout my_tab;
    private ViewPager my_viewpager;
    private List<Fragment> listf = new ArrayList<>();
    private List<String> listt = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_viewpager);
        initView();
        initData();
        initAdapter();
    }

    private void initData() {
        listt.add("全部");
        listt.add("TA回答的");
        listt.add("TA点赞的");
        listt.add("TA提问的");

        my_tab.addTab(my_tab.newTab().setText(listt.get(0)));
        my_tab.addTab(my_tab.newTab().setText(listt.get(1)));
        my_tab.addTab(my_tab.newTab().setText(listt.get(2)));
        my_tab.addTab(my_tab.newTab().setText(listt.get(3)));
        //---------------------------Fragment---------------
        listf.add(new AFragment());
        listf.add(new BFragment());
        listf.add(new CFragment());
        listf.add(new DFragment());

        my_tab.setupWithViewPager(my_viewpager);//关联


    }
    private void initAdapter() {

        MypagerAdapter mypagerAdapter = new MypagerAdapter(getSupportFragmentManager(), listf, listt);
        my_viewpager.setAdapter(mypagerAdapter);
    }

    private void initView() {
        my_tab = (TabLayout) findViewById(R.id.my_tab);
        my_viewpager = (ViewPager) findViewById(R.id.my_viewpager);
    }
}

