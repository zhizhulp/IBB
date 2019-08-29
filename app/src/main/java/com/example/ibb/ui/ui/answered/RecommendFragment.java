package com.example.ibb.ui.ui.answered;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.HomeRecommendAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetFragment;
import com.example.ibb.entity.AddEventBusBean;
import com.example.ibb.entity.BannerBean;
import com.example.ibb.entity.SelectedData;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.utils.GlideImageLoader;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseNetFragment {
    private Banner home_banner;
    private List<SelectedData.DataBean> datas = new ArrayList<>();
    private HomeRecommendAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview(view);
        initdata();
    }

    protected void initview(View view) {
        FragmentActivity activity = getActivity();
        home_banner = (Banner) view.findViewById(R.id.home_banner);
        mRecyclerView.setFocusable(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        adapter=new HomeRecommendAdapter(datas);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ProblemDetailsActivity.startQuestionDet(getActivity(), datas.get(position).getId());
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                SelectedData.DataBean dataBean = datas.get(position);
                if (dataBean.getItemType()!=1){
                    ProblemDetailsActivity.startQuestionDet(getActivity(), datas.get(position).getId());
                }
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                datas.clear();
                adapter.notifyDataSetChanged();
                requestBanner();
                requestQuestionList();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                requestQuestionList();
            }
        });
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(AddEventBusBean addEventBusBean) {
        datas.clear();
        adapter.notifyDataSetChanged();
        requestQuestionList();
    }

    //问题列表
    private void requestQuestionList() {
        com.yanzhenjie.nohttp.rest.Request request = buildPostListRequest(URLApi.questionstring, SelectedData.DataBean.class);
        if (datas.size() > 0)
            request.add("questionId", String.valueOf(datas.get(datas.size() - 1).getId()));
        doRequest(0, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            List<SelectedData.DataBean> dataBeanList = (List<SelectedData.DataBean>) data.getData();
            if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                    mRefreshLayout.getState() == RefreshState.None)
                if (datas.size() > 0) datas.clear();
            if (dataBeanList == null || dataBeanList.size() == 0)
                loadMoreOver();
            if (dataBeanList != null) datas.addAll(dataBeanList);
            adapter.notifyDataSetChanged();
        }
    }

    //banner请求
    private void requestBanner() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("token", getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("token", ""))
                        .build();
                return chain.proceed(request);
            }
        }).build();
        FormBody body = new FormBody.Builder().build();
        Request request = new Request.Builder().url(URLApi.bannerstring).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.e("TAG", "轮播图" + string);
                Gson gson = new Gson();
                final BannerBean bannerBean = gson.fromJson(string, BannerBean.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String image = bannerBean.getData().get(0).getImage();
                        String image1 = bannerBean.getData().get(1).getImage();
                        String image2 = bannerBean.getData().get(2).getImage();
                        String image3 = bannerBean.getData().get(3).getImage();
                        //设置图片加载器
                        home_banner.setImageLoader(new GlideImageLoader());
                        //设置图片集合
                        List<String> imag = new ArrayList<>();
                        imag.add(image);
                        imag.add(image1);
                        imag.add(image2);
                        imag.add(image3);
                        home_banner.setImages(imag);
                        //设置banner动画效果
                        home_banner.setBannerAnimation(Transformer.DepthPage);
                        //设置自动轮播，默认为true
                        home_banner.isAutoPlay(true);
                        //设置轮播时间
                        home_banner.setDelayTime(5000);
                        //设置指示器位置（当banner模式中有指示器时）
                        home_banner.setIndicatorGravity(BannerConfig.CENTER);
                        //banner设置方法全部调用完毕时最后调用
                        home_banner.start();
                    }
                });

            }
        });
    }

    protected void initdata() {
        requestBanner();
        requestQuestionList();
    }

    @Override
    protected boolean canRefreshAndLoad() {
        return true;
    }
}
