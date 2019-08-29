package com.example.ibb.ui.ui.answered;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.ibb.R;
import com.example.ibb.adapter.MySearchDetailsListAdapter;
import com.example.ibb.adapter.MySearchDetailsRecyclerAdapter;
import com.example.ibb.adapter.TwoBeen;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.QABean;
import com.example.ibb.entity.SearchBoxBean;
import com.example.ibb.ui.ui.find.CommodityActivity;
import com.google.gson.Gson;

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

public class SearchDetailsActivity extends BaseNetActivity implements View.OnClickListener {

    private ListView search_details_listView;
    private RecyclerView search_detaila_recyclerView;
    private List<SearchBoxBean.DataBean> searchBoxBeanList = new ArrayList<>();
    private List<QABean.DataBean> qaDataBeanList = new ArrayList<>();
    private List<TwoBeen> beenList = new ArrayList<>();
    private RecyclerAdapterWithHF hf;
    private ImageView searchdetails_back_iv;
    private TextView searchdetails_more_tv;
    private int page = 1;
    private MySearchDetailsListAdapter listAdapter;
    private TextView searchdetails_search_tv;
    private EditText search_editText;
    private String keywords;
    private SearchBoxBean searchBoxBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);
        keywords = getIntent().getStringExtra("keywords");
        initview();
        showSearch();
    }

    protected void initview() {
        //相关商品ListView
        search_details_listView = (ListView)findViewById(R.id.search_details_listView);
        listAdapter = new MySearchDetailsListAdapter(MyApp.activity,searchBoxBeanList);
        search_details_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GoodsDetActivity.startGoodsDet(SearchDetailsActivity.this,searchBoxBeanList.get(position).getId());
            }
        });
        search_details_listView.setAdapter(listAdapter);
        //相关回答
        search_detaila_recyclerView = (RecyclerView)findViewById(R.id.search_detaila_recyclerView);
        search_detaila_recyclerView.setFocusable(false);
        search_detaila_recyclerView.setLayoutManager(new LinearLayoutManager(MyApp.activity,LinearLayoutManager.VERTICAL,false));
        MySearchDetailsRecyclerAdapter recyclerAdapter = new MySearchDetailsRecyclerAdapter(MyApp.activity,qaDataBeanList);
        hf = new RecyclerAdapterWithHF(recyclerAdapter);
        hf.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                ProblemDetailsActivity.startQuestionDet(SearchDetailsActivity.this,qaDataBeanList.get(position).getId());
            }
        });
        search_detaila_recyclerView.setAdapter(hf);
        //返回键
        searchdetails_back_iv =(ImageView)findViewById(R.id.searchdetails_back_iv);
        searchdetails_back_iv.setOnClickListener(this);
        //查看更多
        searchdetails_more_tv = (TextView)findViewById(R.id.searchdetails_more_tv);
        searchdetails_more_tv.setOnClickListener(this);
        //搜索框
        search_editText = (EditText)findViewById(R.id.search_editText);
        //搜索
        searchdetails_search_tv = (TextView)findViewById(R.id.searchdetails_search_tv);
        searchdetails_search_tv.setOnClickListener(this);
    }

    public static void startSearchDet(Context context,String keywords){
        Intent intent=new Intent(context,SearchDetailsActivity.class);
        intent.putExtra("keywords",keywords);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchdetails_back_iv:
                finish();
                break;
            //查看更多
            case R.id.searchdetails_more_tv:
                SearchDetailsMoreActivity.startGoodsList(this,searchBoxBean);
                break;
            //搜索
            case R.id.searchdetails_search_tv:
                keywords=search_editText.getText().toString().trim();
                showSearch();
                break;
        }
    }

    //搜索框
    private void showSearch() {
        //RecyclerView
        OkHttpClient client1 = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("token",getSharedPreferences("user", Context.MODE_PRIVATE).getString("token", ""))
                        .build();
                return chain.proceed(request);
            }
        }).build();
        FormBody body1 = new FormBody.Builder()
                .add("keyword",keywords)
                .add("index",page+"")
                .build();
        Request request1 = new Request.Builder().url(URLApi.qasearchstring).post(body1).build();
        Call call1 = client1.newCall(request1);
        call1.enqueue(new Callback() {
                         @Override
                         public void onFailure(Call call, IOException e) {

                         }

                         @Override
                         public void onResponse(Call call, Response response) throws IOException {
                             final String string = response.body().string();
                             Log.i("TAG", "搜索问答>" + string);
                             runOnUiThread(new Runnable() {
                                 @Override
                                 public void run() {
                                    qaDataBeanList.clear();
                                     Gson gson = new Gson();
                                     QABean qaBean = gson.fromJson(string, QABean.class);
                                     qaDataBeanList.addAll(qaBean.getData());
                                     hf.notifyDataSetChanged();
                                 }
                             });
                         }
                     });
        //ListView相关商品
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
                .add("keyword",keywords)
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
                        searchBoxBean = gson.fromJson(string, SearchBoxBean.class);
                        List<SearchBoxBean.DataBean> data = searchBoxBean.getData();
                        for (int i = 0; i < data.size(); i++) {
                            if (i==2) break;
                            searchBoxBeanList.add(data.get(i));
                        }
                        listAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }
}
