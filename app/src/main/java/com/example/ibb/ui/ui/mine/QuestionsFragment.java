package com.example.ibb.ui.ui.mine;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ibb.R;
import com.example.ibb.adapter.MineRecyclerAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionsFragment extends BaseFragment {


    private View view;
    private RecyclerView questions_recycler;
    private List<String> list = new ArrayList<String>();

    @Override
    protected void initview() {
        view = LayoutInflater.from(MyApp.activity).inflate(R.layout.fragment_questions, null);
        questions_recycler =(RecyclerView)view.findViewById(R.id.questions_recycler);

        //设置RecyclerView不能滑动
        questions_recycler.setFocusable(false);


        questions_recycler.setLayoutManager(new LinearLayoutManager(MyApp.activity,LinearLayoutManager.VERTICAL,false));
        MineRecyclerAdapter mineRecyclerAdapter = new MineRecyclerAdapter(MyApp.activity,list);
        questions_recycler.setAdapter(mineRecyclerAdapter);

    }

    @Override
    protected void initdata() {

        list.add("0000");
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("4444");
    }

    @Override
    protected View initlayout() {
        return view;
    }

    @Override
    protected void restartdata() {

    }

}
