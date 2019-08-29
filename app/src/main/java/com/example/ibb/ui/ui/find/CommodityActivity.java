package com.example.ibb.ui.ui.find;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.adapter.MyCommodityListAdpter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.custom_view.MyListView;
import com.example.ibb.ui.ui.answered.ProductsPjActivity;

import java.util.ArrayList;
import java.util.List;

public class CommodityActivity extends BaseActivity implements View.OnClickListener {

    private MyListView commodity_listview;
    private List<String> list = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private MyRadar radar;
    double[] data={100,40,60,80,50,100,20};
    String[] titles={"性价比5.4","屏幕显示7.3","电池续航8.0","流畅度8.1","拍照效果7.0","手感10.0"};
    private ImageView commodity_back_iv;
    private TextView commoditymore_tv;
    private ImageView commodity_imageview;

    //设置图片加载的地址
    private List<String> images = new ArrayList<>();
    private ImageView commidity_gengduo;
    private Button share_return_popup;
    private LinearLayout commidity_pf;
    private Button tiwen_button;

    @Override
    protected void initview() {
        commodity_listview =(MyListView)findViewById(R.id.commodity_listview);

        MyCommodityListAdpter listAdapter = new MyCommodityListAdpter(MyApp.activity,stringList);
        commodity_listview.setAdapter(listAdapter);


        radar = (MyRadar)findViewById(R.id.radar);
        commodity_back_iv = (ImageView)findViewById(R.id.commodity_back_iv);
        commodity_back_iv.setOnClickListener(this);

//        commoditymore_tv = (TextView)findViewById(R.id.commoditymore_tv);
//        commoditymore_tv.setOnClickListener(this);

        //图片
        commodity_imageview = (ImageView)findViewById(R.id.commodity_imageview);
        commodity_imageview.setOnClickListener(this);

        commidity_gengduo = (ImageView)findViewById(R.id.commidity_gengduo);
        commidity_gengduo.setOnClickListener(this);

        //评分
        commidity_pf = (LinearLayout)findViewById(R.id.commidity_pf);
        commidity_pf.setOnClickListener(this);
        //我要提问
        tiwen_button = (Button)findViewById(R.id.tiwen_button);
        tiwen_button.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

        radar.setData(data);
        radar.setTitles(titles);

        for (int i = 0; i < 3; i++) {
            stringList.add("iphoneX屏幕怎么样，容不容易碎，纠结？"+i);
        }
        for (int i = 0; i < 5; i++) {
            list.add("hahaha"+i);
        }
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_commodity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.commodity_back_iv:
                finish();
                break;

            case R.id.commidity_gengduo:
                showPopupMenu(commidity_gengduo);
                break;

            case R.id.commidity_pf:
                startActivity(new Intent(MyApp.activity,ProductsPjActivity.class));
                break;

            //图片
            case R.id.commodity_imageview:
                startActivity(new Intent(MyApp.activity, GrideImageActivity.class));
                break;
            //我要提问
            case R.id.tiwen_button:
                startActivity(new Intent(MyApp.activity,TiWenActivity.class));
                break;
        }
    }

    /**
     * 菜单
     */
    private void showPopupMenu(View view) {
        PopupMenu popupmenu = new PopupMenu(MyApp.activity,view);
        popupmenu.getMenuInflater().inflate(R.menu.main,popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_share){
                    View view = LayoutInflater.from(MyApp.activity).inflate(R.layout.share_popupwindow,null);
                    final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT,true);
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setBackgroundDrawable(new ColorDrawable());
                    popupWindow.showAtLocation(commidity_gengduo, Gravity.BOTTOM,0,0);//parent view随意
                    share_return_popup = (Button)view.findViewById(R.id.share_return_popup);
                    share_return_popup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });

                    return true;
                }else if (item.getItemId() == R.id.action_jubao){
                    startActivity(new Intent(MyApp.activity,ReportActivity.class));
                    return true;
                }

                return false;
            }
        });
        popupmenu.show();
    }


}
