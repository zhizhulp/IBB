package com.example.ibb.ui.ui.answered;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseFragment;
import com.example.ibb.custom_view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends BaseFragment implements View.OnClickListener {
//    NoScrollViewPager
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    List<String> titleList=new ArrayList<>();
    List<Fragment> fragmentList=new ArrayList<>();
    private ImageView search_imageView;

    @Override
    protected void initview() {

        view = LayoutInflater.from(MyApp.activity).inflate(R.layout.fragment_one, null);

        tabLayout =(TabLayout)view.findViewById(R.id.tabLayout);
        viewPager =(ViewPager)view.findViewById(R.id.viewPager);
        search_imageView =(ImageView)view.findViewById(R.id.search_imageView);

        titleList.add("推荐");
        titleList.add("关注");

        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));

        fragmentList.add(new RecommendFragment());
        fragmentList.add(new AttentionFragment());

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(myViewPagerAdapter);

        //用来绑定viewpager和TabLayout
        tabLayout.setupWithViewPager(viewPager);
        //设置不能滑动
//        viewPager.setNoScroll(true);

        search_imageView.setOnClickListener(this);

    }

    @Override
    protected void initdata() {

    }

    @Override
    protected View initlayout() {

        return view;
    }

    @Override
    protected void restartdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_imageView:
                startActivity(new Intent(MyApp.activity,SearchActivity.class));
                break;
        }
    }

    private class MyViewPagerAdapter extends FragmentPagerAdapter {
            public MyViewPagerAdapter(FragmentManager fm) {
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
