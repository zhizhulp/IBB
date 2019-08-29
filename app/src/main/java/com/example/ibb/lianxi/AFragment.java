package com.example.ibb.lianxi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends BaseFragment {
    private RecyclerView my_recyclerview;
    private View view;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void initview() {
        view = LayoutInflater.from(MyApp.activity).inflate(R.layout.fragment_a, null);
        my_recyclerview = (RecyclerView) view.findViewById(R.id.my_recyclerview);
    }

    @Override
    protected void initdata() {

        //下面的2代表的一行的size是4
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        //设置管理
        my_recyclerview.setLayoutManager(gridLayoutManager);
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(MyApp.activity,stringList);
        //设置适配器
        my_recyclerview.setAdapter(adapter);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            //返回position对应的条目所占的size
            @Override
            public int getSpanSize(int position) {
                if (position < 4)
                    //这里返回4，指的是当position满足上面条件时，一个条目占得size是4
                    //也就是说这个条目占一行，因为上面设置的一行的size是4
                    return 1;
                else if (3 <= position && position < 6)
                    //这里返回2，指的是当position满足上面条件时，一个条目占得size是2
                    // 也就是说这个条目占半行，因为上面设置的一行的size是4
                    return 1;
                else
                    //这里返回1，指的是当position满足上面条件时，一个条目占得size是1
                    // 也就是说这个条目占1/4行，因为上面设置的一行的size是4
                    return 1;
            }
        });
        //用来添加分割线
        //mRecy.addItemDecoration();
    }

    @Override
    protected View initlayout() {
        return view;
    }

    @Override
    protected void restartdata() {

    }

}
