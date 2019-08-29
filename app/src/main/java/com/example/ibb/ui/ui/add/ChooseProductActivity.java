package com.example.ibb.ui.ui.add;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.adapter.MyChooseGridViewAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.entity.BeautyEventBus;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ChooseProductActivity extends BaseActivity implements View.OnClickListener {

    private EditText choose_et;
    private GridView choose_gridView;
    private int[] images = {R.mipmap.icon_phone, R.mipmap.icon_camerafuben,R.mipmap.icon_ipad,R.mipmap.icon_geek,R.mipmap.icon_lipstick,R.mipmap.icon_makeup,R.mipmap.icon_shampoo,R.mipmap.icon_beauty};
    private String[] text = {"手机", "照相机", "ipad", "更多极客", "口红","粉底","洗发水","更多美妆"};
    private TabLayout choose_tabLayout;
    private ViewPager choose_viewPager;
    private List<String> titleList=new ArrayList<>();
    private List<Fragment> fragmentList=new ArrayList<>();
    private TextView choose_addbq_tv;
    private TextView chooseproduct_qx_tv;
    public static ChoosePagerAdapter choosePagerAdapter;
    private SmartRefreshLayout choose_refreshLayout;
    private LinearLayout choose_linearLayout1;
    private int count;
    private List<String> menuList = new ArrayList<>();
    private ArrayList<Object> Title;

    @Override
    protected void initview() {


        EventBus.getDefault().register(this);//订阅

        Title = new ArrayList<>();

        choose_et = (EditText)findViewById(R.id.choose_et);
        choose_gridView = (GridView)findViewById(R.id.choose_gridView);
        MyChooseGridViewAdapter chooseGridViewAdapter = new MyChooseGridViewAdapter(MyApp.activity,images,text);
        choose_gridView.setAdapter(chooseGridViewAdapter);

        choose_tabLayout = (TabLayout)findViewById(R.id.choose_tabLayout);
        choose_viewPager = (ViewPager)findViewById(R.id.choose_viewPager);
        choose_addbq_tv = (TextView)findViewById(R.id.choose_addbq_tv);
        chooseproduct_qx_tv = (TextView)findViewById(R.id.chooseproduct_qx_tv);

        titleList.add("热门极客");
        titleList.add("热门美妆");


        choose_tabLayout.addTab(choose_tabLayout.newTab().setText(titleList.get(0)));
        choose_tabLayout.addTab(choose_tabLayout.newTab().setText(titleList.get(1)));

        fragmentList.add(new PopularGeeksFragment());
        fragmentList.add(new PopularBeautyFragment());

        choosePagerAdapter = new ChoosePagerAdapter(getSupportFragmentManager());
        choose_viewPager.setAdapter(choosePagerAdapter);

        //用来绑定viewpager和TabLayout
        choose_tabLayout.setupWithViewPager(choose_viewPager);
        //避免ListView滑到顶部
        choose_viewPager.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        choose_addbq_tv.setOnClickListener(this);
        chooseproduct_qx_tv.setOnClickListener(this);
        choose_et.setOnClickListener(this);

        //刷新
        choose_refreshLayout =(SmartRefreshLayout)findViewById(R.id.choose_refreshLayout);
        //下拉刷新
        choose_refreshLayout.setRefreshHeader(new ClassicsHeader(MyApp.activity));
        //上拉加载
        choose_refreshLayout.setRefreshFooter(new ClassicsFooter(MyApp.activity));
        //添加标签的LinearLayout
        choose_linearLayout1 = (LinearLayout)findViewById(R.id.choose_linearLayout1);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBeautyEnentBus(BeautyEventBus beautyEventBus){

        Log.e("TAG","-========================================="+beautyEventBus.getId()+",,"+beautyEventBus.getName());

        choose_linearLayout1.removeAllViews();

        initMarksView(beautyEventBus.getName());

    }


    private void initMarksView(String name) {

        final View view = View.inflate(ChooseProductActivity.this, R.layout.label_item, null);
                TextView tv = (TextView) view.findViewById(R.id.textView1);
                tv.setText(name);


        choose_linearLayout1.addView(view);

    }

    @Override
    protected void initdata() {

        choose_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MyApp.activity,ErChooseShoppingActivity.class));
            }
        });

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_choose_product;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chooseproduct_qx_tv:
                finish();
                break;
            case R.id.choose_addbq_tv:

                break;
            case R.id.choose_et:
                startActivity(new Intent(MyApp.activity,ChooseShoppingActivity.class));
                break;
        }
    }

    private class ChoosePagerAdapter extends FragmentStatePagerAdapter{
        public ChoosePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

    }

}
