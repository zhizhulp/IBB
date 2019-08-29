package com.example.ibb.ui.ui.answered;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.beiing.roundimage.CircleImageView;
import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.adapter.MyQuanbuListAdapter;
import com.example.ibb.adapter.MyRenQiListAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.custom_view.MyListView;
import com.example.ibb.custom_view.PileLayout;

import java.util.ArrayList;
import java.util.List;

public class TiDaoProductActivity extends BaseActivity implements View.OnClickListener {

    private ImageView tiproduct_back_iv;
    private List<String> stringList = new ArrayList<>();
    private MyListView renqizuigao_listview;
    private MyListView quanbu_listView;
    private PileLayout pileLayout;
    private String[] urls = {"http://img2.imgtn.bdimg.com/it/u=1939271907,257307689&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1939271907,257307689&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg"
    };
    private MyQuanbuListAdapter quanbuListAdapter;

    @Override
    protected void initview() {

        tiproduct_back_iv = (ImageView)findViewById(R.id.tiproduct_back_iv);
        tiproduct_back_iv.setOnClickListener(this);

        renqizuigao_listview = (MyListView)findViewById(R.id.renqizuigao_listview);
        MyRenQiListAdapter renQiListAdapter = new MyRenQiListAdapter(MyApp.activity,stringList);
        renqizuigao_listview.setAdapter(renQiListAdapter);

        quanbu_listView = (MyListView)findViewById(R.id.quanbu_listView);
        quanbuListAdapter = new MyQuanbuListAdapter(MyApp.activity,stringList);
        quanbu_listView.setAdapter(quanbuListAdapter);

        pileLayout = (PileLayout)findViewById(R.id.pile_layout);
    }

    @Override
    protected void initdata() {

        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < urls.length; i++) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_praise, pileLayout, false);
            Glide.with(this).load(urls[i]).into(imageView);
            pileLayout.addView(imageView);
        }

        for (int i = 0; i <3 ; i++) {
            stringList.add("飘柔精油无硅油洗发水"+i);
        }
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_ti_dao_product;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tiproduct_back_iv:
                finish();
                break;
        }
    }
}
