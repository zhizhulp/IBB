package com.example.ibb.ui.ui.add;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.ibb.R;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.entity.LeftCommodity;
import com.example.ibb.entity.MyEventBusBean;
import com.example.ibb.entity.MyEventBusBean_ForADD;
import com.example.ibb.twocascades.CategoryBean;
import com.example.ibb.twocascades.HomeAdapter;
import com.example.ibb.twocascades.MenuAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ErChooseShoppingActivity extends BaseActivity {

    private ListView lv_menu;
    private TextView tv_title;
    private ListView lv_home;
    private MenuAdapter menuAdapter;
    private List<LeftCommodity> leftCommodityList = new ArrayList<>();
    private List<String> menuList = new ArrayList<>();
    private List<CategoryBean.DataBean> homeList = new ArrayList<>();
    private List<Integer> showTitle;
    private HomeAdapter homeAdapter;
    private int currentItem;

    private LinearLayout linearLayout;
    private List<String> Title;
    private TextView searchresults_addbq_tvd;
    private int count;

    private List<String> delivery;

    @Override
    protected void initview() {

        Title = new ArrayList<>();
        delivery = new ArrayList<>();
        Fresco.initialize(this);
        searchresults_addbq_tvd = (TextView) findViewById(R.id.searchresults_addbq_tvd);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout1);
        lv_menu = (ListView) findViewById(R.id.lv_menu);
        tv_title = (TextView) findViewById(R.id.tv_titile);
        lv_home = (ListView) findViewById(R.id.lv_home);

        searchresults_addbq_tvd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventBus.getDefault().post(new MyEventBusBean_ForADD(delivery));

            }
        });

        //左边listview
        menuAdapter = new MenuAdapter(this, menuList);
        lv_menu.setAdapter(menuAdapter);
        //右边listview
        homeAdapter = new HomeAdapter(this, homeList);
        lv_home.setAdapter(homeAdapter);

        lv_home.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int scrollState;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.scrollState = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    return;
                }
                int current = showTitle.indexOf(firstVisibleItem);
                if (currentItem != current && current >= 0) {
                    currentItem = current;
                    tv_title.setText(menuList.get(currentItem));
                    menuAdapter.setSelectItem(currentItem);
                    menuAdapter.notifyDataSetInvalidated();
                }
            }
        });

        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuAdapter.setSelectItem(position);
                menuAdapter.notifyDataSetInvalidated();
                tv_title.setText(menuList.get(position));
                lv_home.setSelection(showTitle.get(position));
                Title.add(menuList.get(position));
                initMarksView();
            }
        });

    }

    /**
     * 添加标签
     */
    private void initMarksView() {

        for (int i = 0; i < Title.size(); i++) {
            count++;
            if (count <= 2) {
                final View view = View.inflate(ErChooseShoppingActivity.this, R.layout.label_item, null);
                TextView tv = (TextView) view.findViewById(R.id.textView1);
                tv.setText(Title.get(i));
                delivery.add(Title.get(i));
                tv.setTag(i);

                view.setTag(false);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tv1 = (TextView) v.findViewById(R.id.textView1);
                        if (!(Boolean) v.getTag()) {
                            v.setTag(true);
                            tv1.setEnabled(true);
                            tv1.setVisibility(View.GONE);
                            if (tv1.getVisibility()==View.GONE){
                                count = 0;
                                delivery.clear();
                            }
                        }
                    }
                });
                linearLayout.addView(view);
                break;
            }
        }
        Title.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MyEventBusBean messageEvent) {
        Title.add(messageEvent.getMessage());
        initMarksView();
    }

    @Override
    protected void initdata() {

        String json = getJson(this, "category.json");
        CategoryBean categoryBean = JSONObject.parseObject(json, CategoryBean.class);
        showTitle = new ArrayList<>();
        for (int i = 0; i < categoryBean.getData().size(); i++) {
            CategoryBean.DataBean dataBean = categoryBean.getData().get(i);
            menuList.add(dataBean.getModuleTitle());
            showTitle.add(i);
            homeList.add(dataBean);
        }
        tv_title.setText(categoryBean.getData().get(0).getModuleTitle());

        menuAdapter.notifyDataSetChanged();
        homeAdapter.notifyDataSetChanged();
    }

    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_er_choose_shopping;
    }

}
