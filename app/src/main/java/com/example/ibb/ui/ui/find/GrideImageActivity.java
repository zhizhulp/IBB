package com.example.ibb.ui.ui.find;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.ibb.R;
import com.example.ibb.adapter.MyChooseGridViewAdapter;
import com.example.ibb.adapter.MyGridImageAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class GrideImageActivity extends BaseActivity implements View.OnClickListener {

    private GridView gridImage_gv;
    private int[] images = {R.mipmap.xifashui, R.mipmap.xifashui,R.mipmap.xifashui,R.mipmap.xifashui};
    //设置图片加载的地址
    private List<String> image = new ArrayList<>();
    private ImageView grideimage_back_iv;

    @Override
    protected void initview() {
        //返回键
        grideimage_back_iv = (ImageView)findViewById(R.id.grideimage_back_iv);
        grideimage_back_iv.setOnClickListener(this);

        gridImage_gv = (GridView)findViewById(R.id.gridImage_gv);
        MyGridImageAdapter gridImageAdapter = new MyGridImageAdapter(MyApp.activity,images);
        gridImage_gv.setAdapter(gridImageAdapter);


    }

    @Override
    protected void initdata() {
        image.add("http://d.hiphotos.baidu.com/image/pic/item/f31fbe096b63f624f0d3ad2e8c44ebf81a4ca315.jpg");
        image.add("http://d.hiphotos.baidu.com/image/pic/item/5fdf8db1cb134954b2fe01a75f4e9258d0094a15.jpg");
        image.add("http://c.hiphotos.baidu.com/image/pic/item/9825bc315c6034a87ccffc42c2134954082376c7.jpg");
        image.add("http://b.hiphotos.baidu.com/image/pic/item/f3d3572c11dfa9ecf333c4d46bd0f703908fc1d0.jpg");
        image.add("http://upload.365jilin.com/2016/0820/1471686023587.jpg");
        gridImage_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(MyApp.activity, BigImageActivity.class));
            }
        });


    }

    @Override
    protected int initlayout() {
        return R.layout.activity_gride_image;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.grideimage_back_iv:
                finish();
                break;
        }
    }
}
