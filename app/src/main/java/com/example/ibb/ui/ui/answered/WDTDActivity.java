package com.example.ibb.ui.ui.answered;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ibb.R;
import com.example.ibb.adapter.MyWDListAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class WDTDActivity extends BaseActivity implements View.OnClickListener {

    private ImageView wdtd_back_iv;
    private ListView wdtd_listview;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void initview() {

        wdtd_back_iv = (ImageView)findViewById(R.id.wdtd_back_iv);
        wdtd_back_iv.setOnClickListener(this);

        wdtd_listview = (ListView)findViewById(R.id.wdtd_listview);
        MyWDListAdapter listAdapter = new MyWDListAdapter(MyApp.activity,stringList);
        wdtd_listview.setAdapter(listAdapter);
    }

    @Override
    protected void initdata() {
        for (int i = 0; i < 3; i++) {
            stringList.add("飘柔净油无头屑无硅油洗发水"+ i);
        }
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_wdtd;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wdtd_back_iv:
                finish();
                break;

        }
    }
}
