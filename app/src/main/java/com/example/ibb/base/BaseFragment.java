package com.example.ibb.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //视图
        initview();

        initdata();

        return initlayout();
    }

    @Override
    public void onResume() {
        super.onResume();

        restartdata();
    }

    protected abstract void initview();

    protected abstract void initdata();

    protected abstract View initlayout();

    protected abstract void restartdata();
}
