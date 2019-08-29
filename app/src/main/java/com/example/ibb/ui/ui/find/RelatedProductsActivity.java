package com.example.ibb.ui.ui.find;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ibb.R;
import com.example.ibb.adapter.MyRelatedProductsListAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.ui.ui.answered.ProductsPjActivity;

import java.util.ArrayList;
import java.util.List;

public class RelatedProductsActivity extends BaseActivity implements View.OnClickListener {


    private ListView relatedproducts_listview;
    private List<String> stringList = new ArrayList<>();
    private ImageView related_products_iv;

    @Override
    protected void initview() {

        relatedproducts_listview = (ListView)findViewById(R.id.relatedproducts_listview);
        MyRelatedProductsListAdapter listAdapter = new MyRelatedProductsListAdapter(MyApp.activity,stringList);
        relatedproducts_listview.setAdapter(listAdapter);

        related_products_iv = (ImageView)findViewById(R.id.related_products_iv);
        related_products_iv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

        relatedproducts_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MyApp.activity,ProductsPjActivity.class));
            }
        });
        for (int i = 0; i < 5; i++) {
            stringList.add("Apple iPhoneX(A1865) 64G" + i);
        }
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_related_products2;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.related_products_iv:
                finish();
                break;
        }
    }
}
