package com.example.ibb.ui.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.FansAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.User;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.find.PeopleCencerActivity;
import com.example.ibb.utils.AppConfig;
import com.example.ibb.utils.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;

public class MineFansActivity extends BaseNetActivity implements View.OnClickListener {
    private List<User> datas = new ArrayList<>();
    private FansAdapter adapter;
    private int paged=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_fans);
        ImageView fans_back_iv = (ImageView) findViewById(R.id.fans_back_iv);
        fans_back_iv.setOnClickListener(this);
        initRefreshLayout();
        adapter = new FansAdapter(R.layout.fins_list_item, datas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                paged=1;
                requestData();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                paged++;
                requestData();
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                PeopleCencerActivity.startPersonCenter(MineFansActivity.this,datas.get(position).getId());
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                User user = datas.get(position);
                int relation = user.getRelation();
                if (relation==0|| relation==2){
                    user.setRelation(1);
                    requestFocus(position);
                }else {//1 3
                    user.setRelation(0);
                    requestCancelFocus(position);
                }
                adapter.notifyItemChanged(position);
            }
        });

        requestData();
    }
    private void requestData(){
        Request request = buildPostListRequest(URLApi.getFollowers, User.class);
        request.add("userId", AppConfig.getInstance().getLong("id",-1000));
        request.add("index",paged);
        doRequest(0,request);
    }

    private void requestFocus(int position) {
        Request request = buildRequest(URLApi.followUser, HttpBaseEntity.class);
        request.add("userId", datas.get(position).getId());
        doRequest(1, request);
    }

    private void requestCancelFocus(int position) {
        Request request = buildRequest(URLApi.unFollowUser, HttpBaseEntity.class);
        request.add("userId", datas.get(position).getId());
        doRequest(2, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if(what==0){
            if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                    mRefreshLayout.getState() == RefreshState.None)
                if (datas.size() > 0) datas.clear();
            List<User> requestData = (List) data.getData();
            if (requestData == null || requestData.size() == 0) loadMoreOver();
            if (requestData != null) datas.addAll(requestData);
            adapter.notifyDataSetChanged();
        } else if (what == 1) {
            ToastUtils.show("关注用户成功");
        } else if (what == 2) {
            ToastUtils.show("取消关注用户成功");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fans_back_iv:
                finish();
                break;
        }
    }
}
