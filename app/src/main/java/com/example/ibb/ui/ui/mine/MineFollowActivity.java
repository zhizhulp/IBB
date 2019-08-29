package com.example.ibb.ui.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.MyFollowListAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.AnswerBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.answered.AnswerDetActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * 我收藏的回答
 */
public class MineFollowActivity extends BaseNetActivity implements View.OnClickListener {

    private ImageView minefollow_back_iv;
    private List<AnswerBean> datas=new ArrayList<>();
    private MyFollowListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_follow);
        initview();
        requestData();
    }

    protected void initview() {
        minefollow_back_iv = (ImageView)findViewById(R.id.minefollow_back_iv);
        minefollow_back_iv.setOnClickListener(this);

        initRefreshLayout();
        adapter = new MyFollowListAdapter(R.layout.myfollow_list_item,datas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                datas.clear();
                requestData();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                requestData();
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AnswerDetActivity.startAnsDet(MineFollowActivity.this,datas.get(position).getId(),datas.get(position).getTitle());
            }
        });
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                mRefreshLayout.getState() == RefreshState.None)
            if (datas.size() > 0) datas.clear();
        List<AnswerBean> requestData = (List) data.getData();
        if (requestData == null || requestData.size() == 0) loadMoreOver();
        if (requestData != null) datas.addAll(requestData);
        adapter.notifyDataSetChanged();
    }

    private void requestData() {
        Request request = buildPostListRequest(URLApi.getFavoriteAnswers, AnswerBean.class);
        if (datas.size() > 0) {
            AnswerBean answerBean = datas.get(datas.size() - 1);
            request.add("answerId", answerBean.getId());
        }
        doRequest(0, request);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.minefollow_back_iv:
                finish();
                break;


        }
    }
}
