package com.example.ibb.ui.ui.find;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BigImageFragment extends BaseFragment {

    private View view;

    @Override
    protected void initview() {
        view = LayoutInflater.from(MyApp.activity).inflate(R.layout.fragment_big_image, null);
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

}
