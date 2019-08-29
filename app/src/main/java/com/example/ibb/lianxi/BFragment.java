package com.example.ibb.lianxi;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.ibb.R;
import com.example.ibb.adapter.BListAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BFragment extends BaseFragment {

    private View view;
    private ListView b_listview;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void initview() {
        view = LayoutInflater.from(MyApp.activity).inflate(R.layout.fragment_b, null);
        b_listview = (ListView)view.findViewById(R.id.b_listview);
        BListAdapter bListAdapter = new BListAdapter(MyApp.activity,stringList);
        b_listview.setAdapter(bListAdapter);

    }

    @Override
    protected void initdata() {
        for (int i = 0; i < 3; i++) {
            stringList.add(""+i);
        }
    }

    @Override
    protected View initlayout() {
        return view;
    }

    @Override
    protected void restartdata() {

    }

}
