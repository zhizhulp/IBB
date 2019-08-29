package com.example.ibb.lianxi;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ibb.R;
import com.example.ibb.adapter.DRecyclerAdapter;
import com.example.ibb.adapter.TwoBeen;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DFragment extends BaseFragment {

    private View view;
    private RecyclerView d_recyclerView;
    private List<TwoBeen> twoBeenList = new ArrayList<>();

    @Override
    protected void initview() {
        view = LayoutInflater.from(MyApp.activity).inflate(R.layout.fragment_d, null);
        d_recyclerView = (RecyclerView)view.findViewById(R.id.d_recyclerView);
        d_recyclerView.setLayoutManager(new LinearLayoutManager(MyApp.activity,LinearLayoutManager.VERTICAL,false));
        DRecyclerAdapter dRecyclerAdapter = new DRecyclerAdapter(MyApp.activity,twoBeenList);
        d_recyclerView.setAdapter(dRecyclerAdapter);
    }

    @Override
    protected void initdata() {

        twoBeenList.add(new TwoBeen("",1,""));
        twoBeenList.add(new TwoBeen("",2,""));
        twoBeenList.add(new TwoBeen("",1,""));
        twoBeenList.add(new TwoBeen("",2,""));
        twoBeenList.add(new TwoBeen("",1,""));
        twoBeenList.add(new TwoBeen("",2,""));

    }

    @Override
    protected View initlayout() {
        return view;
    }

    @Override
    protected void restartdata() {

    }

}
