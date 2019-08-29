package com.example.ibb.ui.ui.answered;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ibb.R;
import com.example.ibb.adapter.MyDetailsMoreListAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.entity.SearchBoxBean;

import java.util.ArrayList;
import java.util.List;

public class RelatedProductsActivity extends BaseActivity implements View.OnClickListener {

    private List<SearchBoxBean.DataBean> searchBoxBeanList = new ArrayList<>();
    private ListView related_listView;
    private List<String> stringList = new ArrayList<>();
    private ImageView relatedproducts_back_iv;

    @Override
    protected void initview() {

        related_listView = (ListView)findViewById(R.id.related_listView);

        MyDetailsMoreListAdapter listAdapter = new MyDetailsMoreListAdapter(MyApp.activity,searchBoxBeanList);
        related_listView.setAdapter(listAdapter);

        relatedproducts_back_iv = (ImageView)findViewById(R.id.relatedproducts_back_iv);
        relatedproducts_back_iv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

        related_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MyApp.activity,ProductsPjActivity.class));
            }
        });

        for (int i = 0; i < 4; i++) {
            stringList.add("Dior 李小璐口红同款口红非你莫属"+i);
        }
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_related_products;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.relatedproducts_back_iv:
                finish();
                break;
        }
    }
}
