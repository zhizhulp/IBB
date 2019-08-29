package com.example.ibb.lianxi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/15.
 */

public class MypagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listf;
    private List<String> listt;

    public MypagerAdapter(FragmentManager fm, List<Fragment> listf, List<String> listt) {
        super(fm);
        this.listf = listf;
        this.listt = listt;
    }

    @Override
    public Fragment getItem(int position) {
        return listf.get(position);
    }

    @Override
    public int getCount() {
        return listf.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listt.get(position);
    }
}

