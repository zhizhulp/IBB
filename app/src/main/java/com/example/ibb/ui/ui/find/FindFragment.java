package com.example.ibb.ui.ui.find;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.ibb.R;
import com.example.ibb.adapter.MyFindListViewAdapter;
import com.example.ibb.adapter.MyFindListViewAdapter2;
import com.example.ibb.adapter.MyFindRecyclerAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseFragment;
import com.example.ibb.custom_view.MyListView;
import com.example.ibb.entity.BeautyBean;
import com.example.ibb.entity.HighBean;
import com.example.ibb.entity.HotAnswererBean;
import com.example.ibb.entity.LatestBean;
import com.example.ibb.okhttp.OkHttpUtils;
import com.example.ibb.ui.ui.answered.GoodsDetActivity;
import com.example.ibb.ui.ui.answered.ProblemDetailsActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private RecyclerView find_recycler;
    private MyListView find_listview;
    private MyListView find_listview2;
    //最新问题
    private List<LatestBean.DataBean> latestBeanList = new ArrayList<>();
    //热门答主
    private List<HotAnswererBean.DataBean> hotAnswererBeanList = new ArrayList<>();
    //高分商品
    private List<HighBean.DataBean> highBeanList = new ArrayList<>();

    private TextView find_more;
    private TextView find_more_tv;
    private MyFindRecyclerAdapter findRecyclerAdapter;
    private RecyclerAdapterWithHF hf;
    private MyFindListViewAdapter listViewAdapter;
    private MyFindListViewAdapter2 listViewAdapter2;
    private int page = 1;

    @Override
    protected void initview() {
        view = LayoutInflater.from(MyApp.activity).inflate(R.layout.fragment_find, null);
        find_recycler =(RecyclerView)view.findViewById(R.id.find_recycler);
        find_listview =(MyListView) view.findViewById(R.id.find_listview);
        find_listview2 =(MyListView)view.findViewById(R.id.find_listview2);
        find_more =(TextView)view.findViewById(R.id.find_more);
        find_more_tv = (TextView)view.findViewById(R.id.find_more_tv);

        //热门答主
        find_recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        findRecyclerAdapter = new MyFindRecyclerAdapter(MyApp.activity,hotAnswererBeanList);
        hf = new RecyclerAdapterWithHF(findRecyclerAdapter);
        find_recycler.setAdapter(hf);
        //最新问题
        listViewAdapter = new MyFindListViewAdapter(MyApp.activity,latestBeanList);
        find_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProblemDetailsActivity.startQuestionDet(getActivity(),latestBeanList.get(position).getId());
            }
        });
        find_listview.setAdapter(listViewAdapter);
        //高分商品
        listViewAdapter2 = new MyFindListViewAdapter2(MyApp.activity,highBeanList);
        find_listview2.setAdapter(listViewAdapter2);

        find_more.setOnClickListener(this);
        find_more_tv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {
        //最新问题
        zxquestion();
        //热门答主
        hotrequest();
        //高分商品
        highrequest();
        //热门答主
        hf.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                //跳转别人个人中心界面
                PeopleCencerActivity.startPersonCenter(getActivity(),hotAnswererBeanList.get(position).getId());
            }
        });
        //最新问题
        find_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProblemDetailsActivity.startQuestionDet(getActivity(),latestBeanList.get(position).getId());
            }
        });
        //高分商品
        find_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转商品详情界面
                GoodsDetActivity.startGoodsDet(getActivity(),highBeanList.get(position).getId());
            }
        });
    }

    /**
     * 高分商品请求
     */
    private void highrequest() {
        OkHttpClient highokHttpClient = new OkHttpClient.Builder().build();
        FormBody highformBody = new FormBody.Builder()
                .add("index",page + "")
                .build();
        Request highrequest = new Request.Builder().url(URLApi.highstring).post(highformBody).build();
        Call highcall = highokHttpClient.newCall(highrequest);
        highcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e("TAG","高分商品"+ string);
                Gson  gson = new Gson();
                HighBean highBean = gson.fromJson(string, HighBean.class);
                if (highBean.getMeta().isSuccess()){
                    highBeanList.addAll((Collection<? extends HighBean.DataBean>)highBean.getData());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listViewAdapter2.notifyDataSetChanged();
                        }
                    });

                }

            }
        });
    }

    /**
     * 热门答主请求
     */
    private void hotrequest() {
        OkHttpClient hotokHttpClient = new OkHttpClient.Builder().build();
        FormBody hotformBody = new FormBody.Builder().build();
        Request hotrequest = new Request.Builder().url(URLApi.hotAnswererstring).post(hotformBody).build();
        Call hotcall = hotokHttpClient.newCall(hotrequest);
        hotcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Gson gson = new Gson();
                HotAnswererBean hotAnswererBean = gson.fromJson(string, HotAnswererBean.class);
                if (hotAnswererBean.getMeta().isSuccess()){
                    hotAnswererBeanList.addAll((Collection<? extends HotAnswererBean.DataBean>)hotAnswererBean.getData());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hf.notifyDataSetChanged();

                        }
                    });
                }

            }
        });
    }

    /**
     * 最新问题请求
     */
    private void zxquestion() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody latestformBody = new FormBody.Builder().build();
        Request latestrequest = new Request.Builder().url(URLApi.lateststring).post(latestformBody).build();
        Call latestcall = okHttpClient.newCall(latestrequest);
        latestcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i("TAG","-------"+string);
                Gson gson = new Gson();
                LatestBean latestBean1 = gson.fromJson(string, LatestBean.class);
                if (latestBean1.getMeta().isSuccess()){
                    latestBeanList.addAll((Collection<? extends LatestBean.DataBean>)latestBean1.getData());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listViewAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected View initlayout() {
        return view;
    }

    @Override
    protected void restartdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.find_more:
                //热门答主（查看更多）
                startActivity(new Intent(MyApp.activity,MoreActivity.class));
                break;
            case R.id.find_more_tv:
                //最新问题（查看更多）
                Intent intent = new Intent(MyApp.activity,FindMoreActivity.class);
                startActivity(intent);
                break;
        }
    }


}
