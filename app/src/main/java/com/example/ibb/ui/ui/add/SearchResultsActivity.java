package com.example.ibb.ui.ui.add;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.adapter.MySearchResultsListAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.custom_view.XCFlowLayout;
import com.example.ibb.entity.SearchBoxBean;
import com.example.ibb.ui.ui.answered.MineAnsweredBean;
import com.example.ibb.apimanager.URLApi;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchResultsActivity extends BaseActivity implements View.OnClickListener {


    private MineAnsweredBean check_bean = new MineAnsweredBean();

    private TextView searchresults_qx_tv;
    private ListView searchresults_lv;
    private List<SearchBoxBean.DataBean> searchBoxBeanList = new ArrayList<>();
    private XCFlowLayout searchresult_flowLayout;
    private ImageView flow_delete;
    private RelativeLayout flow_relative;
    private TextView searchresults_addbq_tv;


    private String list_check = "";
    private TextView searchresult_search_tv;
    private EditText searchresult_et;
    private int page = 1;
    private MySearchResultsListAdapter listAdapter;
    private SmartRefreshLayout searchresults_refreshLayout;


    @Override
    protected void initview() {

        //添加标签/商品
        searchresults_addbq_tv = (TextView)findViewById(R.id.searchresults_addbq_tv);
        searchresults_addbq_tv.setOnClickListener(this);
        //搜索框
        searchresult_et = (EditText)findViewById(R.id.searchresult_et);
        searchresult_et.setOnClickListener(this);
        //取消
        searchresults_qx_tv = (TextView)findViewById(R.id.searchresults_qx_tv);
        searchresults_qx_tv.setOnClickListener(this);
        //搜索
        searchresult_search_tv = (TextView)findViewById(R.id.searchresult_search_tv);
        searchresult_search_tv.setOnClickListener(this);
        //ListView
        searchresults_lv = (ListView)findViewById(R.id.searchresults_lv);
        listAdapter = new MySearchResultsListAdapter(MyApp.activity,searchBoxBeanList);
        searchresults_lv.setAdapter(listAdapter);

        //刷新
        searchresults_refreshLayout =(SmartRefreshLayout)findViewById(R.id.searchresults_refreshLayout);
        //下拉刷新
        searchresults_refreshLayout.setRefreshHeader(new ClassicsHeader(MyApp.activity));
        //上拉加载
        searchresults_refreshLayout.setRefreshFooter(new ClassicsFooter(MyApp.activity));

        searchresults_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

             CheckBox checkBox =  view.findViewById(R.id.searchresult_cb);


                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        1、把数据改成实体类形式
//                      2、
                        Log.e("TAG","当前位置：pos="+position);

                        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.leftMargin = 30;
//                            for (int i = 0; i < 1; i++) {
                        final View view = LayoutInflater.from(SearchResultsActivity.this).inflate(R.layout.flow_item,null);
                        flow_relative =(RelativeLayout)view.findViewById(R.id.flow_relative);
                        flow_delete = (ImageView)view.findViewById(R.id.flow_delete);
                        flow_delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                    view.setVisibility(View.GONE);
//                                searchresult_flowLayout.removeAllViews();
                                searchresult_flowLayout.removeView(view);
                            }
                        });
//                            }
                        searchresult_flowLayout.addViews(position,view,layoutParams);

                    }
                });

            }
        });

        searchresult_flowLayout =(XCFlowLayout)findViewById(R.id.searchresult_flowLayout);
//        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.leftMargin = 30;
//        for (int i = 0; i < 1; i++) {
//            final View view = LayoutInflater.from(this).inflate(R.layout.flow_item,null);
//            flow_relative =(RelativeLayout)view.findViewById(R.id.flow_relative);
//            flow_delete = (ImageView)view.findViewById(R.id.flow_delete);
//            flow_delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    view.setVisibility(View.GONE);
//                    searchresult_flowLayout.removeView(view);
//                }
//            });
//        }

    }

    @Override
    protected void initdata() {


    }

    @Override
    protected int initlayout() {
        return R.layout.activity_search_results;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchresults_qx_tv:
                finish();
                break;
            case R.id.searchresults_addbq_tv:

                Intent it = new Intent();

                MineAnsweredBean bean = new MineAnsweredBean();

                it.putExtra("list_check",bean);

                setResult(1002,it);
                finish();

                break;
            //搜索
            case R.id.searchresult_search_tv:
                setSearchBut(searchresult_et.getText().toString().trim());
                break;
        }
    }

    private void setSearchBut(final String searchbox) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("token",getSharedPreferences("user", Context.MODE_PRIVATE).getString("token", ""))
                        .build();
                return chain.proceed(request);
            }
        }).build();
       FormBody body = new FormBody.Builder()
                .add("keyword",searchbox)
                .add("index",page+"")
                .build();
        Request request = new Request.Builder().url(URLApi.searchstring).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i("TAG","--->"+ string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        searchBoxBeanList.clear();
                        Gson gson = new Gson();
                        SearchBoxBean searchBoxBean = gson.fromJson(string, SearchBoxBean.class);
                        searchBoxBeanList.addAll(searchBoxBean.getData());
                        listAdapter.notifyDataSetChanged();
                    }
                });

            }
        });

    }
}
