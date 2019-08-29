package com.example.ibb.ui.ui.find;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.ibb.R;
import com.example.ibb.adapter.MyProductGridAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;

public class ProductImageActivity extends BaseActivity implements View.OnClickListener {

    private GridView productimage_gridView;
    private int[] images = {R.mipmap.minebackground, R.mipmap.minebackground,R.mipmap.minebackground,R.mipmap.minebackground,R.mipmap.minebackground};
    private String[] text = {"Dior,和张凯雅同款", "圣罗兰 妖媚给你想要", "卡姿兰口红持久", "笨蛋", "iPhone X"};
    private ImageView productimage_back_iv;

    @Override
    protected void initview() {
        productimage_back_iv =(ImageView)findViewById(R.id.productimage_back_iv);

        productimage_gridView =(GridView)findViewById(R.id.productimage_gridView);
        MyProductGridAdapter gridAdapter = new MyProductGridAdapter(MyApp.activity,images,text);
        productimage_gridView.setAdapter(gridAdapter);

        productimage_back_iv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_product_image;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.productimage_back_iv:
                finish();
                break;
        }
    }
}
